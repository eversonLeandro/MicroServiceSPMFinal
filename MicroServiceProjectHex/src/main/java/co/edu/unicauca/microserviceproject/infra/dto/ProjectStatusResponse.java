package co.edu.unicauca.microserviceproject.infra.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectStatusResponse {
    String estado;
    String mensaje;

    public ProjectStatusResponse(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

}
