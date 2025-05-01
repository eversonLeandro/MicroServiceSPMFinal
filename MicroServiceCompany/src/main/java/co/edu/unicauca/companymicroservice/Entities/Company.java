package co.edu.unicauca.companymicroservice.Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @Column(length = 15, nullable = false)
    private String nit;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sector sector = Company.Sector.OTROS;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.HABILITADO;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contacto> contactos;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> proyectos;


    public Company() {
    }

    public Company(String nit, String nombre, Estado estado,String sector,List<Contacto> contactos,List<Project> proyectos ){
        this.nit = nit;
        this.nombre = nombre;
        this.estado = estado;
        this.sector = Company.Sector.valueOf(sector.toUpperCase());
        this.proyectos = proyectos;
        this.contactos = contactos;

    }


    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public List<Project> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Project> proyectos) {
        this.proyectos = proyectos;
    }

    public enum Estado {
        HABILITADO,
        DESHABILITADO
    }

    public enum Sector {
        TECNOLOGIA,
        SALUD,
        EDUCATION,
        OTROS
    }

    public Sector getSector(){
        return sector;
    }
    public void setSector(Sector sector){
        this.sector =sector;
    }

}
