package co.edu.unicauca.microserviceemailnotification.infra.dto;

public class NotificationStatus {
    private String nombreProyecto;
    private String estado;
    private String correoEmpresa;
    private String correoCordinador;

    public NotificationStatus() {}
    public NotificationStatus(String nombreProyecto, String estado, String correoEmpresa,String correoCordinador) {
        this.nombreProyecto = nombreProyecto;
        this.estado = estado;
        this.correoEmpresa = correoEmpresa;
        this.correoCordinador = correoCordinador;
    }

    public String getCorreoCordinador() {
        return correoCordinador;
    }

    public void setCorreoCordinador(String correoCordinador) {
        this.correoCordinador = correoCordinador;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCorreoEmpresa() {
        return correoEmpresa;
    }

    public void setCorreoEmpresa(String correoEmpresa) {
        this.correoEmpresa = correoEmpresa;
    }
}
