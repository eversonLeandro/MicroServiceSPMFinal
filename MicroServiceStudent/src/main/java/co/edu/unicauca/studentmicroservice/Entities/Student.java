package co.edu.unicauca.studentmicroservice.Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(length = 15, nullable = false)
    private String codEst;

    @Column(length = 15, nullable = false, unique = true)
    private String cedula;

    @Column(length = 15, nullable = false)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.HABILITADO;

    @OneToOne
    @JoinColumn(name = "userName", referencedColumnName = "userName")
    private Usuario usuario;

    @ManyToMany(mappedBy = "students")
    private List<Team> teams;

    public Student() {
    }

    public Student(String codEst, String cedula, String telefono, Usuario usuario) {
        this.codEst = codEst;
        this.cedula = cedula;
        this.telefono = telefono;
        this.estado = Estado.HABILITADO;
        this.usuario = usuario;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public enum Estado {
        HABILITADO,
        DESHABILITADO
    }
}
