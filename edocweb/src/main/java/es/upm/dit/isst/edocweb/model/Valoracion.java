package es.upm.dit.isst.edocweb.model;

import javax.validation.constraints.Email;




public class Valoracion {
    
    
    private int id;

    private String nombreAsignatura;

    @Email
    private String email;

    @Email
    private String profesor;

    private int puntuacionProfesor;

    private int puntuacionAsignatura;

    private String comentarioAsignatura; //VARCHAR 255, mejorar para el Sprint3

    private String comentarioProfesor; //VARCHAR 255, mejorar para el Sprint3

    private String comentarioRespuesta;

    private String fecha;
    
    public Valoracion() {
    }


    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNombreAsignatura() {
        return nombreAsignatura;
    }


    public void setNombreAsignatura(String asignatura) {
        this.nombreAsignatura = asignatura;
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

    public int getPuntuacionProfesor() {
        return puntuacionProfesor;
    }

    public void setPuntuacionProfesor(int puntuacionProfesor) {
        this.puntuacionProfesor = puntuacionProfesor;
    }

    public int getPuntuacionAsignatura() {
        return puntuacionAsignatura;
    }

    public void setPuntuacionAsignatura(int puntuacionAsignatura) {
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
