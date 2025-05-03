package co.edu.unicauca.companymicroservice.Entities;

import jakarta.persistence.*;

import jakarta.persistence.*;

@Entity
@Table(name = "contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContacto")
    private Long idContacto;

    @Column(length = 50, nullable = false)
    private String apellido;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 15, nullable = false)
    private String telefono;

    @Column(length = 50, nullable = false)
    private String cargo;
    @Column(length = 50, nullable = false)
    private String email;

    public Contacto() {
    }

    public Contacto(String apellido, String cargo, String email, String nombre, String telefono) {
        this.apellido = apellido;
        this.cargo = cargo;
        this.email = email;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setIdContacto(Long idContacto) {
        this.idContacto = idContacto;
    }

    public Long getIdContacto() {
        return idContacto;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
