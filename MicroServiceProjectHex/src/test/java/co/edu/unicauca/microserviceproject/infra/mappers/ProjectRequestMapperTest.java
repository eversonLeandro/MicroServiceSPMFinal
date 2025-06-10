package co.edu.unicauca.microserviceproject.infra.mappers;

import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.*;
import co.edu.unicauca.microserviceproject.domain.model.project.state.RecibidoState;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectRequest;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectRequestMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectRequestMapperTest {

    private ProjectRequestMapper mapper = new ProjectRequestMapper();

    @Test
    void testFromProjectRequestToProject() {
        ProjectRequest request = ProjectRequest.builder()
                .nitCompany(900123456L)
                .coordinatorId(123L)
                .nombre("Test Project")
                .resumen("Resumen")
                .descripcion("Descripción")
                .objetivo("Objetivo")
                .TiempoMaximo("6 meses")
                .presupuesto("1000000")
                .FechaEntregadaEsperada("30/12/2025")
                .periodoAcademico("2025-1")
                .build();

        Project project = mapper.fromProjectRequestToProject(request);

        assertNotNull(project);
        assertEquals("Test Project", project.getNombre());
        assertEquals(new BigDecimal("1000000"), project.getPresupuesto().getValor());
        assertEquals("2025-1", project.getPeriodoAcademico().getPeriodo());
        assertTrue(project.getEstado() instanceof RecibidoState);
    }
}