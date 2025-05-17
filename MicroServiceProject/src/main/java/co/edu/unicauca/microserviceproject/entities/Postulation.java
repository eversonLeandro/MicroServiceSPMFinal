package co.edu.unicauca.microserviceproject.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Postulation {

    @Id
    private Long idPostulation;

    private Long codStudent;
    private Long codProject;

    public Long getIdPostulation() {
        return idPostulation;
    }

    public void setIdPostulation(Long idPostulation) {
        this.idPostulation = idPostulation;
    }

    private LocalDateTime  fecha;

    public Long getCodStudent() {
        return codStudent;
    }

    public void setCodStudent(Long codStudent) {
        this.codStudent = codStudent;
    }

    public Long getCodProject() {
        return codProject;
    }

    public void setCodProject(Long codProject) {
        this.codProject = codProject;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Postulation(Long idPostulation,Long codStudent_, Long codProject_, LocalDateTime fecha_) {
        this.idPostulation = idPostulation;
        this.codStudent = codStudent_;
        this.codProject = codProject_;
        this.fecha = fecha_;
    }

    public Postulation() {

    }
}
