package es.upm.dit.isst.edocapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.edocapi.model.Valoracion;

public interface ValoracionRepository extends CrudRepository<Valoracion, Integer> {
}