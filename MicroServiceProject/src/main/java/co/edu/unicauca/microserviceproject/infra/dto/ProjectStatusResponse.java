package co.edu.unicauca.microserviceproject.infra.dto;

public class ProjectStatusResponse {
    private String estado;
    private String mensaje;

    public ProjectStatusResponse(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public String getEstado() {
        return estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
