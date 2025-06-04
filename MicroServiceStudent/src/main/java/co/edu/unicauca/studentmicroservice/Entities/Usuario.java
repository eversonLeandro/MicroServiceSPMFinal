package co.edu.unicauca.studentmicroservice.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "userName", length = 50, nullable = false)
    private String userName;

    @Column(name = "pass", length = 255, nullable = false)
    private String pass;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Rol rol;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado = Estado.HABILITADO;

    public Usuario() {
    }

    public Usuario(String userName, String pass, String email, Estado estado) {
        this.userName = userName;
        this.pass = pass;
        this.email = email;
        this.rol = Rol.valueOf("HABILITADO");
        this.estado = estado;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public enum Rol {
        ESTUDIANTE,
        COORDINADOR,
        EMPRESA
    }

    public enum Estado {
        HABILITADO,
        DESHABILITADO
    }
}
