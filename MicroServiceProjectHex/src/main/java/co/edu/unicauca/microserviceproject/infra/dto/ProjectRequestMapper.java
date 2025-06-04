package co.edu.unicauca.microserviceproject.infra.dto;

import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.FechaEntrega;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.PeriodoAcademico;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.Presupuesto;
import co.edu.unicauca.microserviceproject.domain.model.project.state.RecibidoState;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component

public class ProjectRequestMapper {
    public Project fromProjectRequestToProject(ProjectRequest request) {
    return new Project(
            new RecibidoState(),
            request.getNitCompany(),
            request.getCoordinatorId(),
            request.getNombre(),
            request.getResumen(),
            request.getDescripcion(),
            request.getObjetivo(),
            request.getTiempoMaximo(),
            Presupuesto.of(new BigDecimal(request.getPresupuesto())),
            FechaEntrega.of(LocalDate.parse(request.getFechaEntregadaEsperada(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
            PeriodoAcademico.of(request.getPeriodoAcademico())
    );
    }
}
