package co.edu.unicauca.companymicroservice.Entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "project")
public class Project {

    @Id
    private Long idProject;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Column(length = 255, nullable = false)
    private String titulo;

    @Column(precision = 20, scale = 2, nullable = false)
    private BigDecimal presupuesto;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String objetivo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String resumen;

    @Column(length = 100, nullable = false)
    private String tiempoEst;

    @Column(length = 200, nullable = false)
    private String fechaEntregaEsperada;

    @ManyToOne
    @JoinColumn(name = "nit", nullable = false)
    private Company company;



    public Project() {
    }

    public Project(String descripcion, String titulo, BigDecimal presupuesto, String objetivo,
                   String resumen, String tiempoEst, Estado estado, String fechaEntregaEsperada,
                   Company company) {
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.presupuesto = presupuesto;
        this.objetivo = objetivo;
        this.resumen = resumen;
        this.tiempoEst = tiempoEst;
        this.fechaEntregaEsperada = fechaEntregaEsperada;
        this.company = company;
    }

    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigDecimal getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(BigDecimal presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getTiempoEst() {
        return tiempoEst;
    }

    public void setTiempoEst(String tiempoEst) {
        this.tiempoEst = tiempoEst;
    }

    public String getFechaEntregaEsperada() {
        return fechaEntregaEsperada;
    }

    public void setFechaEntregaEsperada(String fechaEntregaEsperada) {
        this.fechaEntregaEsperada = fechaEntregaEsperada;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public enum Estado {
        RECIBIDO,
        ACEPTADO,
        RECHAZADO,
        EJECUCION,
        CERRADO
    }
}
