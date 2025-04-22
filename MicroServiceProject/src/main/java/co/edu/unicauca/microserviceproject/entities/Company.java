package co.edu.unicauca.microserviceproject.entities;

import jakarta.persistence.*;

@Entity
public class Company {

    @Id
    private Long nit;
    private String nombre;

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
    }
    @PrePersist
    public void prePersist(){
        if(estado == null) {
            estado = "ACTIVO";
        }
    }
}
