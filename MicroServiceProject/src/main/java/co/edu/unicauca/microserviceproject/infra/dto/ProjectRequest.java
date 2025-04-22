package co.edu.unicauca.microserviceproject.infra.dto;

import java.util.List;

public class ProjectRequest {


    private String estadoTexto;

    private Long nitCompany;
    private Long codCor;

    private String nombre;
    private String resumen;
    private String descripcion;
    private String objetivo;
    private String tiempoMaximo;
    private String presupuesto;
    private String fechaEntregadaEsperada;

    private List<Long> idPostulaciones;

    // Getters y Setters
    public String getEstadoTexto() {
        return estadoTexto;
    }

    public void setEstadoTexto(String estadoTexto) {
        this.estadoTexto = estadoTexto;
    }

    public Long getNitCompany() {
        return nitCompany;
    }

    public void setNitCompany(Long nitCompany) {
        this.nitCompany = nitCompany;
    }

    public Long getCodCor() {
        return codCor;
    }

    public void setCodCor(Long codCor) {
        this.codCor = codCor;
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

    public List<Long> getIdPostulaciones() {
        return idPostulaciones;
    }

    public void setIdPostulaciones(List<Long> idPostulaciones) {
        this.idPostulaciones = idPostulaciones;
    }
}
