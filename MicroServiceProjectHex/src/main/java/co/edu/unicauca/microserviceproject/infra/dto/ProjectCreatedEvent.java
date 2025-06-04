package co.edu.unicauca.microserviceproject.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProjectCreatedEvent {
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

}

