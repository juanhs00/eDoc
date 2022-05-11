package es.upm.dit.isst.edocapi.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.edocapi.model.Asignatura;
import es.upm.dit.isst.edocapi.model.Persona;
import es.upm.dit.isst.edocapi.model.Valoracion;
import es.upm.dit.isst.edocapi.repository.AsignaturaRepository;
import es.upm.dit.isst.edocapi.repository.ValoracionRepository;

@RestController

public class AsignaturaController {

    private final AsignaturaRepository asignaturaRepository;
    private final ValoracionRepository valoracionRepository;

    public static final Logger log = LoggerFactory.getLogger(AsignaturaController.class);

    public AsignaturaController(AsignaturaRepository t, ValoracionRepository v) {

        this.asignaturaRepository = t;
        this.valoracionRepository = v;

    }

    @GetMapping("/asignaturas")

    List<Asignatura> readAll() {

      return (List<Asignatura>) asignaturaRepository.findAll();

    }

 

    @PostMapping("/asignaturas")

    ResponseEntity<Asignatura> create(@RequestBody Asignatura newAsignatura) throws URISyntaxException {

      Asignatura result = asignaturaRepository.save(newAsignatura);

      return ResponseEntity.created(new URI("/asignaturas/" + result.getNombre())).body(result);

    }

 

    @GetMapping("/asignaturas/{id}")

    ResponseEntity<Asignatura> read(@PathVariable String id) {


      

      return asignaturaRepository.findById(id).map(asignatura ->

         ResponseEntity.ok().body(asignatura)

      ).orElse(new ResponseEntity<Asignatura>(HttpStatus.NOT_FOUND));

    }


    @PutMapping("/asignaturas/{id}")

    ResponseEntity<Asignatura> update(@RequestBody Asignatura newAsignatura, @PathVariable String id) {

      return asignaturaRepository.findById(id).map(asignatura -> {

        asignatura.setNombre(newAsignatura.getNombre());

        asignatura.setProfesor(newAsignatura.getProfesor());

        asignatura.setAlumnos(newAsignatura.getAlumnos());

        asignaturaRepository.save(asignatura);

        return ResponseEntity.ok().body(asignatura);

      }).orElse(new ResponseEntity<Asignatura>(HttpStatus.NOT_FOUND));

    }


    @DeleteMapping("/asignaturas/{id}")

    ResponseEntity<Asignatura> delete(@PathVariable String id) {

      asignaturaRepository.deleteById(id);

      return ResponseEntity.ok().body(null);

    }

    @GetMapping("/asignaturas/profesor/{id}")

    List<Asignatura> readProfesor(@PathVariable String id) {

      List<Asignatura> asignaturas = (List<Asignatura>) asignaturaRepository.findAll();
      List<Asignatura> result = new ArrayList<>();
      for (Asignatura asignatura : asignaturas) {

        if (asignatura.getProfesor().equals(id)) {

          result.add(asignatura);

        }

      }
      return result;
    }

    @GetMapping("/asignaturas/alumno/{id}")

    List<Asignatura> readAlumno(@PathVariable String id) {

      List <Asignatura> list = (List<Asignatura>) asignaturaRepository.findAll();
      List <Asignatura> listaAlumno = new ArrayList<>();

      for(Asignatura asignatura: list){
        List<Persona> list_personas = asignatura.getAlumnos();
        for(Persona p: list_personas){
          if(p.getEmail().equals(id)){
            listaAlumno.add(asignatura);
          }
        }
      }

      /*for (Asignatura asignatura : list) {
        if (asignatura.getAlumnos().equals(id)){//el fallo esta aqui
            listaAlumno.add(asignatura);
        }
      }*/

      return listaAlumno;

    }

    @GetMapping("/asignaturas/info_agregada/{id}")

    List<Valoracion> showInfoAsignaturas(@PathVariable String id) {

      List <Asignatura> asignaturas = (List<Asignatura>) asignaturaRepository.findAll();
      String nombre_asignatura = "";
      List <Valoracion> valoraciones = (List<Valoracion>) valoracionRepository.findAll();
      String email = ""; //nombre alumno anonimizado

      List <Valoracion> valoracionesAsignatura = new ArrayList<>();

      for (Asignatura asignatura: asignaturas){
        if (asignatura.getNombre().equals(id)){
          nombre_asignatura = asignatura.getNombre();
          break;
        }
      }

      for (Valoracion valoracion : valoraciones) {
        if ((String) valoracion.getNombreAsignatura() == (String) nombre_asignatura){
            valoracion.setEmail(email);
            valoracionesAsignatura.add(valoracion);
        }
      }

      return valoracionesAsignatura;

    }

    @GetMapping("/jefe_estudios")

    List<Asignatura> readAsignaturas(@PathVariable String id) {

      List <Asignatura> asignaturas = (List<Asignatura>) asignaturaRepository.findAll();
      return asignaturas;
    }

    @PostMapping("/jefe_estudios")

    ResponseEntity<Asignatura> createPage(@RequestBody Asignatura newAsignatura) throws URISyntaxException {

      Asignatura result = asignaturaRepository.save(newAsignatura);

      return ResponseEntity.created(new URI("/jefe_estudios/" + result.getNombre())).body(result);
    }

    @GetMapping("/jefe_estudios/{id}")

    List<Valoracion> readInfoJE(@PathVariable String id) {

      List <Asignatura> asignaturas = (List<Asignatura>) asignaturaRepository.findAll();
      String nombre_asignatura = "";
      List <Valoracion> valoraciones = (List<Valoracion>) valoracionRepository.findAll();
      String email = ""; //nombre alumno anonimizado
      int contador = 0;
      double sumas [] = new double[2]; //[0] = media puntuacion asignatura ; [1] = media puntuacion profesor
      sumas[0]=0.0;
      sumas[1]=0.0;
      List <Valoracion> valoracionesAsignatura = new ArrayList<>();

      if(asignaturas.size()==0 || valoraciones.size()==0){
        return valoracionesAsignatura; //CAMBIO
      }
      

      for (Asignatura asignatura: asignaturas){
        if (asignatura.getNombre().equals(id)){
          nombre_asignatura = asignatura.getNombre();
          break;
        }
      }

      for (Valoracion valoracion : valoraciones) {
        if ((String) valoracion.getNombreAsignatura() == (String) nombre_asignatura){
            valoracion.setEmail(email);
            valoracionesAsignatura.add(valoracion);
            contador++;
            sumas[0] += valoracion.getPuntuacionAsignatura();
            sumas[1] += valoracion.getPuntuacionProfesor();
        }
      }

      
      
      //crear una valoracion con la media de las puntuaciones de la asignatura y del profesor
      Valoracion valoracionMedia = new Valoracion();
      
      valoracionMedia.setPuntuacionAsignatura(sumas[0]/contador);
      valoracionMedia.setPuntuacionProfesor(sumas[1]/contador);
      
      
      valoracionesAsignatura.add(valoracionMedia);
      if(valoraciones.size()==1){
        valoraciones.get(0).setPuntuacionAsignatura(0.0);
        valoraciones.get(0).setPuntuacionProfesor(0.0);
      }
      
      return valoracionesAsignatura; //para que no fuera muy complicado todo, la ultima valoracion es la
                                     //media de las puntuaciones de la asignatura y del profesor

    }

    
}