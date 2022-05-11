package es.upm.dit.isst.edocapi.model;


import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VALORACION")
public class Valoracion {
    
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    
    @Column(name = "NOMBREASIGNATURA")
    private String nombreAsignatura;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PROFESOR")
    private String profesor;

    @Column(name = "PUNTUACIONPROFESOR")
    private double puntuacionProfesor;

    @Column(name = "PUNTUACIONASIGNATURA")
    private double puntuacionAsignatura;

    @Column(name = "COMENTARIOASIGNATURA", length = 255)
    private String comentarioAsignatura; //VARCHAR 255, mejorar para el Sprint3

    @Column(name = "COMENTARIOPROFESOR", length = 255)
    private String comentarioProfesor; //VARCHAR 255, mejorar para el Sprint3

    @Column(name = "COMENTARIORESPUESTA")
    private String comentarioRespuesta;

    @Column(name = "FECHA")
    private String fecha;
    
    public Valoracion() {
    }


    public String getProfesor() {
        return profesor;
    }


    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getFecha() {
        return fecha;
    }


    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public String getComentarioRespuesta() {
        return comentarioRespuesta;
    }

    public void setComentarioRespuesta(String comentarioRespuesta) {
        this.comentarioRespuesta = comentarioRespuesta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public double getPuntuacionProfesor() {
        return puntuacionProfesor;
    }

    public void setPuntuacionProfesor(double puntuacionProfesor) {
        this.puntuacionProfesor = puntuacionProfesor;
    }

    public double getPuntuacionAsignatura() {
        return puntuacionAsignatura;
    }

    public void setPuntuacionAsignatura(double puntuacionAsignatura) {
        this.puntuacionAsignatura = puntuacionAsignatura;
    }

  
    public String getComentarioAsignatura() {
        return comentarioAsignatura;
    }

    public void setComentarioAsignatura(String comentarioAsignatura) {
        this.comentarioAsignatura = comentarioAsignatura;
    }

    public String getComentarioProfesor() {
        return comentarioProfesor;
    }

    public void setComentarioProfesor(String comentarioProfesor) {
        this.comentarioProfesor = comentarioProfesor;
    }

    
   
    

    
}
