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

import es.upm.dit.isst.edocapi.model.Valoracion;
import es.upm.dit.isst.edocapi.repository.ValoracionRepository;


@RestController

public class ValoracionController {

    private final ValoracionRepository valoracionRepository;

    public static final Logger log = LoggerFactory.getLogger(ValoracionController.class);

    public ValoracionController(ValoracionRepository v) {

        this.valoracionRepository = v;

    }

    @GetMapping("/valoraciones")

    List<Valoracion> readAll() {

      return (List<Valoracion>) valoracionRepository.findAll();

    }

 

     @PostMapping("/valoraciones")

    ResponseEntity<Valoracion> create(@RequestBody Valoracion newValoracion) throws URISyntaxException {

      Valoracion result = valoracionRepository.save(newValoracion);

      return ResponseEntity.created(new URI("/valoraciones/" + result.getId())).body(result); //todo

    }

 

    @GetMapping("/valoraciones/{id}") 

    ResponseEntity<Valoracion> read(@PathVariable Integer id) {

      return valoracionRepository.findById(id).map(valoracion ->

         ResponseEntity.ok().body(valoracion)

      ).orElse(new ResponseEntity<Valoracion>(HttpStatus.NOT_FOUND));

    }


    @PutMapping("/valoraciones/{id}")

    ResponseEntity<Valoracion> update(@RequestBody Valoracion newValoracion, @PathVariable Integer id) {

      return valoracionRepository.findById(id).map(valoracion -> {


        valoracion.setPuntuacionAsignatura(newValoracion.getPuntuacionAsignatura());

        valoracion.setPuntuacionProfesor(newValoracion.getPuntuacionProfesor());

        valoracion.setComentarioAsignatura(newValoracion.getComentarioAsignatura());

        valoracion.setComentarioProfesor(newValoracion.getComentarioProfesor());

        valoracion.setComentarioRespuesta(newValoracion.getComentarioRespuesta()); 

        valoracion.setFecha(newValoracion.getFecha()); //fijar automaticamente
       
        valoracionRepository.save(valoracion);

        return ResponseEntity.ok().body(valoracion);

      }).orElse(new ResponseEntity<Valoracion>(HttpStatus.NOT_FOUND));

    }


    @DeleteMapping("valoraciones/{id}") 

    ResponseEntity<Valoracion> delete(@PathVariable Integer id) {

      valoracionRepository.deleteById(id);

      return ResponseEntity.ok().body(null);

    } 

    @GetMapping("/valoraciones/profesor/{id}")

    List<Valoracion> readProfesor(@PathVariable String id) {

      List<Valoracion> valoraciones = (List<Valoracion>) valoracionRepository.findAll();
      List<Valoracion> result = new ArrayList<>();
      for (Valoracion valoracion : valoraciones) {
        if (valoracion.getProfesor().equals(id)) {
          result.add(valoracion);

        }

      }
      return result;
    }
}