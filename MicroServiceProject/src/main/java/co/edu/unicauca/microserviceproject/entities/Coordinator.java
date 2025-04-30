package co.edu.unicauca.microserviceproject.entities;


import jakarta.persistence.*;

@Entity
public class Coordinator {

    @Id
    private Long codCor;

    private String estado;

    private String gmail;

    public Coordinator() {

    }
    public Coordinator(Long codCor,String estado, String gmail) {
        this.codCor = codCor;
        this.estado = estado;
        this.gmail = gmail;
    }

    public Long getCodCor() {
        return codCor;
    }

    public void setCodCor(Long codCor) {
        this.codCor = codCor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    @PrePersist
    public void prePersist() {
        if (estado == null) {
            estado = "ACTIVO";
        }
    }
}
