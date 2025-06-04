package co.edu.unicauca.studentmicroservice.Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(length = 10, nullable = false,unique=true)
    private String codEst;
    @Column(length = 30, nullable = false,unique = true)
    private String nombre;
    @Column(length = 10, nullable = false, unique = true)
    private String cedula;
    @Column(length = 10, nullable = false)
    private String telefono;
    @Column(length = 35, nullable = false)
    private String email;



    public Student() {
    }

    public Student(String cedula, String codEst, String email, String nombre, String telefono) {
        this.cedula = cedula;
        this.codEst = codEst;
        this.email = email;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getCodEst() {
        return codEst;
    }

    public void setCodEst(String codEst) {
        this.codEst = codEst;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}


}
