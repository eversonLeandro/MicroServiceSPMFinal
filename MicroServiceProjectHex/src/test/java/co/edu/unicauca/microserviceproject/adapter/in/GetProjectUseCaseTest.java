package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.out.IProjectRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.FechaEntrega;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.PeriodoAcademico;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.Presupuesto;
import co.edu.unicauca.microserviceproject.domain.model.project.state.RecibidoState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetProjectUseCaseTest {

    @Mock
    private IProjectRepositoryPort projectRepositoryPort;

    @InjectMocks
    private GetProjectUseCase getProjectUseCase;

    @Test
    void testFindProjectByProjectId_Success() {
        Project expectedProject = new Project(
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

        when(projectRepositoryPort.findProjectByProjectId(1L)).thenReturn(expectedProject);

        Project result = getProjectUseCase.findProjectByProjectId(1L);

        assertNotNull(result);
        assertEquals("Test Project", result.getNombre());
        verify(projectRepositoryPort, times(1)).findProjectByProjectId(1L);
    }

    @Test
    void testFindProjectByProjectId_NotFound() {
        when(projectRepositoryPort.findProjectByProjectId(999L)).thenReturn(null);

        Project result = getProjectUseCase.findProjectByProjectId(999L);

        assertNull(result);
    }
}