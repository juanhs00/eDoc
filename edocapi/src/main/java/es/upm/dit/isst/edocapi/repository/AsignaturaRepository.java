package es.upm.dit.isst.edocapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.edocapi.model.Asignatura;
import es.upm.dit.isst.edocapi.model.Persona;
import es.upm.dit.isst.edocapi.model.Valoracion;

public interface AsignaturaRepository extends CrudRepository<Asignatura, String> {

    List<Asignatura> findByProfesor(String Profesor);
    List<Asignatura> findByValoracion(Valoracion valoracion); 
}
