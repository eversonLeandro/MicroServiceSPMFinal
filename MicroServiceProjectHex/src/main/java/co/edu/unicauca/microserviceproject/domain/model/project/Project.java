package co.edu.unicauca.microserviceproject.domain.model.project;

import co.edu.unicauca.microserviceproject.domain.model.project.VO.FechaEntrega;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.PeriodoAcademico;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.Presupuesto;
import co.edu.unicauca.microserviceproject.domain.model.project.state.ProjectState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Project {

    private Long idProject;
    private ProjectState estado;
    private Long companyId;
    private Long coordinatorId;

    private String nombre;
    private String resumen;
    private String descripcion;
    private String objetivo;
    private String tiempoMaximo;

    private Presupuesto presupuesto;
    private FechaEntrega fechaEntregaEsperada;
    private PeriodoAcademico periodoAcademico;

    public Project(ProjectState estado, Long companyId, Long coordinatorId,
                   String nombre, String resumen, String descripcion, String objetivo,
                   String tiempoMaximo, Presupuesto presupuesto,
                   FechaEntrega fechaEntregaEsperada, PeriodoAcademico periodoAcademico) {

        this.estado = estado;
        this.companyId = companyId;
        this.coordinatorId = coordinatorId;
        this.nombre = nombre;
        this.resumen = resumen;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.tiempoMaximo = tiempoMaximo;
        this.presupuesto = presupuesto;
        this.fechaEntregaEsperada = fechaEntregaEsperada;
        this.periodoAcademico = periodoAcademico;
    }
    public void cambiarEstado(ProjectState nuevoEstado) {
        this.estado = nuevoEstado;
    }

}
