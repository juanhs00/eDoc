package es.upm.dit.isst.edocapi.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ASIGNATURA")
public class Asignatura {

    
    
    @Id
    @Column(name = "NOMBRE_ASIGNATURA")
    private String nombre;

    @Column(name = "PROFESOR")
    private String profesor;

    @ManyToMany(targetEntity = Persona.class, cascade = CascadeType.ALL)
    @Column(name = "ALUMNOS")
    private List<Persona> alumnos;

    @OneToMany(targetEntity = Valoracion.class, cascade = CascadeType.ALL)
    @Column(name = "VALORACION")
    private Set<Valoracion> valoracion;

    @Column(name = "NOTAPROFESOR")
    private float notaProfesor;

    @Column(name = "NOTAASIGNATURA")
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

    public List<Persona> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Persona> alumnos) {
        this.alumnos = alumnos;
    }

    public Set<Valoracion> getValoracion() {
        return valoracion;
    }

    public void setValoracion(Set<Valoracion> valoracion) {
        this.valoracion = valoracion;
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