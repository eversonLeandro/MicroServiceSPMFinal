package co.edu.unicauca.microserviceproject.entities;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Company {

    @Id
    @NotNull
    private Long nit;
    private String nombre;
    private String email;


    @OneToMany(mappedBy = "company")
    private List<Project> projects = new ArrayList<>();

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    private String estado;
    public Company() {
    }

    public Company(Long nit, String nombre,String estado) {
        this.nit = nit;
        this.nombre = nombre;
        estado = "ACTIVO";
    }
    public Company(Long nit, String nombre,String estado,String email) {
        this.nit = nit;
        this.nombre = nombre;
        estado = "ACTIVO";
        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @PrePersist
    public void prePersist(){
        if(estado == null) {
            estado = "ACTIVO";
        }
    }

}
