/*package es.upm.dit.isst.edocapi.controller;

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
import es.upm.dit.isst.edocapi.repository.PersonaRepository;
import es.upm.dit.isst.edocapi.repository.ValoracionRepository;

@RestController
public class PersonaController {

    private final PersonaRepository personaRepository;
    private final AsignaturaRepository asignaturaRepository;

    public static final Logger log = LoggerFactory.getLogger(PersonaController.class); 

    public PersonaController(PersonaRepository p, AsignaturaRepository t) {

        this.asignaturaRepository = t;
        this.personaRepository = p;

    }

    @GetMapping("/personas")

    List<Persona> readAll() {

      return (List<Persona>) personaRepository.findAll();

    }

 

    @PostMapping("/personas")

    ResponseEntity<Persona> create(@RequestBody Persona newPersona) throws URISyntaxException {

      Persona result = personaRepository.save(newPersona);

      return ResponseEntity.created(new URI("/personas/" + result.getEmail())).body(result);

    }

    @GetMapping("/personas/{id}")

    ResponseEntity<Persona> read(@PathVariable String id) {

      return personaRepository.findById(id).map(persona ->

         ResponseEntity.ok().body(persona)

      ).orElse(new ResponseEntity<Persona>(HttpStatus.NOT_FOUND));

    }


    @PutMapping("/personas/{id}")

    ResponseEntity<Persona> update(@RequestBody Persona newPersona, @PathVariable String id) {

      return personaRepository.findById(id).map(persona -> {

        persona.setEmail(newPersona.getEmail());

        persona.setPassword(newPersona.getPassword());

        persona.setAsignatura(newPersona.getAsignatura());

        personaRepository.save(persona);

        return ResponseEntity.ok().body(persona);

      }).orElse(new ResponseEntity<Persona>(HttpStatus.NOT_FOUND));

    }
    
}*/
