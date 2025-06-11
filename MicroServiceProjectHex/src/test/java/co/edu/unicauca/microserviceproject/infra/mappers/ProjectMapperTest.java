package co.edu.unicauca.microserviceproject.infra.mappers;

import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.state.RecibidoState;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.*;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectCreatedEvent;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectDto;
import co.edu.unicauca.microserviceproject.infra.entities.ProjectEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectMapperTest {

    @Test
    void testDomainToEntity() {
        Project project = new Project(
                1L,
                new RecibidoState(),
                900123456L,
                123L,
                "Test Project",
                "Resumen",
                "Descripcion",
                "Objetivo",
                "6 meses",
                Presupuesto.of(new BigDecimal("1000000")),
                FechaEntrega.of(LocalDate.of(2025, 12, 30)),
                PeriodoAcademico.of("2025-1")
        );

        ProjectEntity entity = ProjectMapper.domainToEntity(project);

        assertNotNull(entity);
        assertEquals(1L, entity.getProjectId());
        assertEquals("Test Project", entity.getNombre());
        assertEquals("RECIBIDO", entity.getEstado());
    }

    @Test
    void testEntityToDomain() {
        ProjectEntity entity = new ProjectEntity(
                1L,
                900123456L,
                123L,
                "Test Project",
                "Resumen",
                "Descripcion",
                "Objetivo",
                "6 meses",
                "1000000",
                "30/12/2025",
                "RECIBIDO",
                "2025-1"
        );

        Project project = ProjectMapper.entityToDomain(entity);

        assertNotNull(project);
        assertEquals(1L, project.getIdProject());
        assertEquals("Test Project", project.getNombre());
        assertEquals("RECIBIDO", project.getEstado().getEstado());
    }

    @Test
    void testDomainToDto() {
        Project project = new Project(
                1L,
                new RecibidoState(),
                900123456L,
                123L,
                "Test Project",
                "Resumen",
                "Descripcion",
                "Objetivo",
                "6 meses",
                Presupuesto.of(new BigDecimal("1000000")),
                FechaEntrega.of(LocalDate.of(2025, 12, 30)),
                PeriodoAcademico.of("2025-1")
        );

        ProjectDto dto = ProjectMapper.domainToDto(project);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Test Project", dto.getNombre());
        assertEquals("RECIBIDO", dto.getEstadoTexto());
    }

    @Test
    void testDomainToEvent() {
        Project project = new Project(
                1L,
                new RecibidoState(),
                900123456L,
                123L,
                "Test Project",
                "Resumen",
                "Descripcion",
                "Objetivo",
                "6 meses",
                Presupuesto.of(new BigDecimal("1000000")),
                FechaEntrega.of(LocalDate.of(2025, 12, 30)),
                PeriodoAcademico.of("2025-1")
        );

        ProjectCreatedEvent event = ProjectMapper.domainToEvent(project);

        assertNotNull(event);
        assertEquals(1L, event.getId());
        assertEquals("Test Project", event.getNombre());
        assertEquals("1000000", event.getPresupuesto());
    }
}