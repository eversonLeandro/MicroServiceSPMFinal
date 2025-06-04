package co.edu.unicauca.companymicroservice.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "coordinator")
public class Coordinator {

    @Id
    @Column(length = 15, nullable = false)
    private String codCor;

    @Column(length = 15, nullable = false, unique = true)
    private String cedula;

    @Column(length = 15, nullable = false)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.HABILITADO;


    public Coordinator() {
    }

    public Coordinator(String codCor, String cedula, String telefono, Estado estado) {
        this.codCor = codCor;
        this.cedula = cedula;
        this.telefono = telefono;
        this.estado = estado;
    }

    // GETTERS Y SETTERS

    public String getCodCor() {
        return codCor;
    }

    public void setCodCor(String codCor) {
        this.codCor = codCor;
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

    // Enum interno
    public enum Estado {
        HABILITADO,
        DESHABILITADO
    }
}
