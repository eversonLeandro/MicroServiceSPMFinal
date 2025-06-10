package co.edu.unicauca.microserviceproject.infra.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationStatus {
    private String nombreProyecto;
    private String estado;
    private String correoEmpresa;
    private String correoCordinador;

    public NotificationStatus() {}
    public NotificationStatus(String nombreProyecto, String estado, String correoEmpresa, String correoCordinador) {
        this.nombreProyecto = nombreProyecto;
        this.estado = estado;
        this.correoEmpresa = correoEmpresa;
        this.correoCordinador = correoCordinador;
    }

}
