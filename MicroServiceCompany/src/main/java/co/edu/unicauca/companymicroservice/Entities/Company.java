package co.edu.unicauca.companymicroservice.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(length = 50, nullable = false)  // Nuevo campo email
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sector sector = Company.Sector.OTROS;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.HABILITADO;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contacto_id", referencedColumnName = "idContacto")
    @JsonIgnore
    @JsonManagedReference
    private Contacto contacto;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> proyectos;

    public Company(){}

    public Company(String nombre, String email, Sector sector, Estado estado, Contacto contacto, List<Project> proyectos) {
        this.nombre = nombre;
        this.email = email;
        this.sector = sector;
        this.estado = estado;
        this.contacto = contacto;
        this.proyectos = proyectos;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
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

}
