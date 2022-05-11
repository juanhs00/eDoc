package es.upm.dit.isst.edocweb.model;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class Asignatura {
    
    @NotEmpty
    private String nombre;

    @Email
    private String profesor;

    private Set<Persona> alumnos;

    private Set<Valoracion> valoraciones;

    private float notaProfesor;

    private float notaAsignatura;

    public Asignatura() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public Set<Persona> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Set<Persona> alumnos) {
        this.alumnos = alumnos;
    }

    public Set<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(Set<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public float getNotaProfesor() {
        return notaProfesor;
    }

    public void setNotaProfesor(float notaProfesor) {
        this.notaProfesor = notaProfesor;
    }

    public float getNotaAsignatura() {
        return notaAsignatura;
    }

    public void setNotaAsignatura(float notaAsignatura) {
        this.notaAsignatura = notaAsignatura;
    }
    
    

    
}
