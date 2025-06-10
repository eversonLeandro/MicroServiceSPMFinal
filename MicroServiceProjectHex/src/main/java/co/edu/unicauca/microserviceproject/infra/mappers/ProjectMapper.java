package co.edu.unicauca.microserviceproject.infra.mappers;

import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.ProjectWithCompany;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.FechaEntrega;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.PeriodoAcademico;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.Presupuesto;
import co.edu.unicauca.microserviceproject.domain.model.project.state.*;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectCreatedEvent;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectDto;
import co.edu.unicauca.microserviceproject.infra.entities.ProjectEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ProjectMapper {

    public static Project entityToDomain(ProjectEntity savedProject) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new Project(
             savedProject.getProjectId(),
             crearEstado(savedProject.getEstado()),
             savedProject.getCompanyId(),
             savedProject.getCoordinatorId(),
             savedProject.getNombre(),
             savedProject.getResumen(),
             savedProject.getDescripcion(),
             savedProject.getObjetivo(),
             savedProject.getTiempoMaximo(),
             Presupuesto.of(new BigDecimal(savedProject.getPresupuesto())),
             FechaEntrega.of(LocalDate.parse(savedProject.getFechaEntregadaEsperada(),formatter)),
             PeriodoAcademico.of(savedProject.getPeriodoAcademico())
        );

    }
    public static ProjectEntity domainToEntity(Project project) {
        if (project == null) return null;
        return new ProjectEntity(
                project.getIdProject(),
                project.getCompanyId(),
                project.getCoordinatorId(),
                project.getNombre(),
                project.getResumen(),
                project.getDescripcion(),
                project.getObjetivo(),
                project.getTiempoMaximo(),
                project.getPresupuesto().getValor().toString(),
                project.getFechaEntregaEsperada().getFechaFormateada(),
                project.getEstado().getEstado(),
                project.getPeriodoAcademico().getPeriodo().toString()
        );
    }
    public static ProjectDto domainToDto(Project project) {
        return new ProjectDto(
                project.getIdProject(),
                project.getCompanyId(),
                project.getNombre(),
                project.getResumen(),
                project.getDescripcion(),
                project.getObjetivo(),
                project.getTiempoMaximo(),
                project.getPresupuesto().getValor().toString(),
                project.getFechaEntregaEsperada().getFechaFormateada(),
                project.getEstado().getEstado(),
                project.getPeriodoAcademico().getPeriodo(),
                null

        );
    }

    public static ProjectState crearEstado(String tipoEstado) {
        if (tipoEstado == null) {
            throw new IllegalArgumentException("El tipo de estado no puede ser nulo");
        }

        return switch (tipoEstado.toUpperCase()) {
            case "RECIBIDO" -> new RecibidoState();
            case "ACEPTADO" -> new AceptadoState();
            case "RECHAZADO" -> new RechazadoState();
            case "EN EJECUCION" -> new EnEjecucionState();
            case "CERRADO" -> new CerradoState();
            default -> throw new IllegalArgumentException("Estado no válido: " + tipoEstado);
        };
    }

    public static ProjectCreatedEvent domainToEvent(Project savedProject) {
        return new ProjectCreatedEvent(
                savedProject.getIdProject(),
                savedProject.getCompanyId(),
                savedProject.getNombre(),
                savedProject.getResumen(),
                savedProject.getDescripcion(),
                savedProject.getObjetivo(),
                savedProject.getTiempoMaximo(),
                String.valueOf(savedProject.getPresupuesto().getValor()),
                savedProject.getFechaEntregaEsperada().getFechaFormateada(),
                savedProject.getEstado().getEstado(),
                savedProject.getPeriodoAcademico().getPeriodo()

        );
    }
    public static ProjectDto projectWithCompanyToProjectDto(ProjectWithCompany projectWithCompany) {
        return new ProjectDto(
                projectWithCompany.getProject().getIdProject(),
                projectWithCompany.getProject().getCompanyId(),
                projectWithCompany.getProject().getNombre(),
                projectWithCompany.getProject().getResumen(),
                projectWithCompany.getProject().getDescripcion(),
                projectWithCompany.getProject().getObjetivo(),
                projectWithCompany.getProject().getTiempoMaximo(),
                String.valueOf(projectWithCompany.getProject().getPresupuesto().getValor()),
                projectWithCompany.getProject().getFechaEntregaEsperada().getFechaFormateada(),
                projectWithCompany.getProject().getEstado().getEstado(),
                projectWithCompany.getProject().getPeriodoAcademico().getPeriodo(),
                projectWithCompany.getCompany().getNombre()
        );
    }
}
