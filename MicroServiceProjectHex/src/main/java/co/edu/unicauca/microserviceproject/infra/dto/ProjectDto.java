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
    private String periodoAcademico;
    private String nombreEmpresa;

}
