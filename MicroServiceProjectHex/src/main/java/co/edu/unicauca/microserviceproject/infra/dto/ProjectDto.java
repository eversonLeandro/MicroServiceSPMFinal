package co.edu.unicauca.microserviceproject.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class ProjectDto {
    private Long idProject;
    private Long companyId;
    private Long coordinatorId;
    private String nombre;
    private String resumen;
    private String descripcion;
    private String objetivo;
    private String tiempoMaximo;
    private String estado;
    private String fechaEntrega;


}
