package co.edu.unicauca.microserviceproject.domain.model.project.state;

import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.FechaEntrega;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.PeriodoAcademico;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.Presupuesto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectStateTest {

    private Project createTestProject() {
        return new Project(
                1L,
                new RecibidoState(),
                900123456L,
                123L,
                "Test Project",
                "Resumen",
                "Descripción",
                "Objetivo",
                "6 meses",
                Presupuesto.of(new BigDecimal("1000000")),
                FechaEntrega.of(LocalDate.now().plusMonths(6)),
                PeriodoAcademico.of("2025-1")
        );
    }

    @Test
    void testRecibidoStateAvanzar() {
        Project project = createTestProject();
        ProjectState newState = project.getEstado().avanzarEstado(project);
        assertTrue(newState instanceof AceptadoState);
    }

    @Test
    void testRecibidoStateNoAvanzar() {
        Project project = createTestProject();
        ProjectState newState = project.getEstado().noAvanzaEstado(project);
        assertTrue(newState instanceof RechazadoState);
    }

    @Test
    void testAceptadoStateAvanzar() {
        Project project = createTestProject();
        project.cambiarEstado(new AceptadoState());
        ProjectState newState = project.getEstado().avanzarEstado(project);
        assertTrue(newState instanceof EnEjecucionState);
    }

    @Test
    void testEnEjecucionStateAvanzar() {
        Project project = createTestProject();
        project.cambiarEstado(new EnEjecucionState());
        ProjectState newState = project.getEstado().avanzarEstado(project);
        assertTrue(newState instanceof CerradoState);
    }

    @Test
    void testCerradoStateNoCambia() {
        Project project = createTestProject();
        project.cambiarEstado(new CerradoState());
        ProjectState newState = project.getEstado().avanzarEstado(project);
        assertTrue(newState instanceof CerradoState);
    }
}