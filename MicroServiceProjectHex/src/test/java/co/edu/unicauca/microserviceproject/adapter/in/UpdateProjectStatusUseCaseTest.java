package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.out.*;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.coordinator.Coordinator;
import co.edu.unicauca.microserviceproject.domain.model.coordinator.VO.Email;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.FechaEntrega;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.PeriodoAcademico;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.Presupuesto;
import co.edu.unicauca.microserviceproject.domain.model.project.state.*;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectStatusResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateProjectStatusUseCaseTest {

    @Mock
    private IProjectRepositoryPort projectRepositoryPort;

    @Mock
    private ICompanyRepositoryPort companyRepositoryPort;

    @Mock
    private ICoordinatorRepositoryPort coordinatorRepositoryPort;

    @Mock
    private EventPublisherPort eventPublisherPort;

    @InjectMocks
    private UpdateProjectStatusUseCase updateProjectStatusUseCase;

    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project(
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

        when(projectRepositoryPort.findProjectByProjectId(anyLong())).thenReturn(project);
    }

    @Test
    void testUpdateStatus_AvanzarFromRecibido() {
        when(companyRepositoryPort.findCompanyByNit(anyLong()))
                .thenReturn(Optional.of(new Company(900123456L, "Test Company",
                        new co.edu.unicauca.microserviceproject.domain.model.company.VO.Email("test@company.com"))));

        when(coordinatorRepositoryPort.findCoordinatorById(anyLong()))
                .thenReturn(Optional.of(new Coordinator(123L, "ACTIVO",
                        new co.edu.unicauca.microserviceproject.domain.model.coordinator.VO.Email("coord@test.com"))));

        ProjectStatusResponse response = updateProjectStatusUseCase.updateProjectStatus(1L, "avanzar");

        assertEquals("ACEPTADO", response.getEstado());
        assertEquals("El proyecto ha sido ACEPTADO.", response.getMensaje());
        verify(projectRepositoryPort, times(1)).saveProject(any(Project.class));
        verify(eventPublisherPort, times(1)).publishNotificationStatusEvent(any());
    }

    @Test
    void testUpdateStatus_NoAvanzarFromRecibido() {
        when(projectRepositoryPort.findProjectByProjectId(anyLong())).thenReturn(project);
        when(companyRepositoryPort.findCompanyByNit(anyLong()))
                .thenReturn(Optional.of(new Company(900123456L,"Test Company",new co.edu.unicauca.microserviceproject.domain.model.company.VO.Email("test@company.com"))));
        when(coordinatorRepositoryPort.findCoordinatorById(anyLong()))
                .thenReturn(Optional.of(new Coordinator(123L,"ACTIVO",new co.edu.unicauca.microserviceproject.domain.model.coordinator.VO.Email("coord@test.com"))));

        ProjectStatusResponse response = updateProjectStatusUseCase.updateProjectStatus(1L, "noavanzar");

        assertEquals("RECHAZADO", response.getEstado());
        assertEquals("El proyecto ha sido RECHAZADO.", response.getMensaje());
    }

    @Test
    void testUpdateStatus_ProjectNotFound() {
        when(projectRepositoryPort.findProjectByProjectId(anyLong())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            updateProjectStatusUseCase.updateProjectStatus(1L, "avanzar");
        });
    }
}