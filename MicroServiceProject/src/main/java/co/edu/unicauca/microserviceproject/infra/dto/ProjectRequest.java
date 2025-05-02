package co.edu.unicauca.microserviceproject.infra.dto;

import co.edu.unicauca.microserviceproject.entities.Company;
import co.edu.unicauca.microserviceproject.entities.Project;

import java.util.Optional;

public class ProjectRequest {

    private Long nitCompany;
    private Long codCor;
    private String nombre;
    private String resumen;
    private String descripcion;
    private String objetivo;
    private String TiempoMaximo;
    private String presupuesto;
    private String FechaEntregadaEsperada;

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
        return TiempoMaximo;
    }

    public void setTiempoMaximo(String tiempoMaximo) {
        TiempoMaximo = tiempoMaximo;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getFechaEntregadaEsperada() {
        return FechaEntregadaEsperada;
    }

    public void setFechaEntregadaEsperada(String fechaEntregadaEsperada) {
        FechaEntregadaEsperada = fechaEntregadaEsperada;
    }
}
