package co.edu.unicauca.microserviceproject.infra.dto;

import java.io.Serializable;

public class ProjectRequestCompany {
    private Long id;
    private Long nitCompany;
    private String nombre;
    private String resumen;
    private String descripcion;
    private String objetivo;
    private String tiempoMaximo;
    private String presupuesto;
    private String fechaEntregadaEsperada;
    private String estadoTexto;


    public String getEstadoTexto() {
        return estadoTexto;
    }

    public void setEstadoTexto(String estadoTexto) {
        this.estadoTexto = estadoTexto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNitCompany() {
        return nitCompany;
    }

    public void setNitCompany(Long nitCompany) {
        this.nitCompany = nitCompany;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(String tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getFechaEntregadaEsperada() {
        return fechaEntregadaEsperada;
    }

    public void setFechaEntregadaEsperada(String fechaEntregadaEsperada) {
        this.fechaEntregadaEsperada = fechaEntregadaEsperada;
    }
    public ProjectRequestCompany clone() {
        ProjectRequestCompany cloned = new ProjectRequestCompany();

        // Copiar todos los campos
        cloned.setId(this.id); // Long es inmutable
        cloned.setNitCompany(this.nitCompany);
        cloned.setNombre(this.nombre); // String es inmutable
        cloned.setResumen(this.resumen);
        cloned.setDescripcion(this.descripcion);
        cloned.setObjetivo(this.objetivo);
        cloned.setTiempoMaximo(this.tiempoMaximo);
        cloned.setPresupuesto(this.presupuesto);
        cloned.setFechaEntregadaEsperada(this.fechaEntregadaEsperada);

        return cloned;
    }
}
