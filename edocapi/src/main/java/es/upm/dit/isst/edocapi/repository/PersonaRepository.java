package es.upm.dit.isst.edocapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.edocapi.model.Asignatura;
import es.upm.dit.isst.edocapi.model.Persona;

public interface PersonaRepository extends CrudRepository<Persona, String> {
    
    //List<Persona> findById(Persona persona);
}
