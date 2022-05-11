package es.upm.dit.isst.edocapi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="PERSONA")
public class Persona {
    
    @Id
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(targetEntity = Asignatura.class, cascade = CascadeType.ALL)
    @Column(name = "ASIGNATURA")
    private List <Asignatura> asignatura;

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



    public List <Asignatura> getAsignatura() {
        return asignatura;
    }



    public void setAsignatura(List <Asignatura> asignatura) {
        this.asignatura = asignatura;
    }

  

   
    

    
}
