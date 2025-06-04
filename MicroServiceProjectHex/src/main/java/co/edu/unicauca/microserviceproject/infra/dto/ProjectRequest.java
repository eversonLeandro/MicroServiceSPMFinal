package co.edu.unicauca.microserviceproject.infra.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProjectRequest {

    private Long nitCompany;
    private Long coordinatorId;
    private String nombre;
    private String resumen;
    private String descripcion;
    private String objetivo;
    private String TiempoMaximo;
    private String presupuesto;
    private String FechaEntregadaEsperada;
    private String periodoAcademico;

}
