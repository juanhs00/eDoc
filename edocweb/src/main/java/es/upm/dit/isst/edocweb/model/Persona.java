package es.upm.dit.isst.edocweb.model;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class Persona {
    
    @Email
    private String email;

    @NotEmpty
    private String password;

    private Set<Asignatura> asignaturas; //METER SET<Asignatura> ??? en ese caso con todos los amarillos

    public Persona() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Set<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

}