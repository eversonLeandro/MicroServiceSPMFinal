package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.out.EventPublisherPort;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICompanyRepositoryPort;
import co.edu.unicauca.microserviceproject.aplication.port.out.IProjectRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.*;
import co.edu.unicauca.microserviceproject.domain.model.project.state.RecibidoState;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectCreatedEvent;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProjectUseCaseTest {

    @Mock
    private IProjectRepositoryPort projectRepositoryPort;

    @Mock
    private ICompanyRepositoryPort companyRepositoryPort;

    @Mock
    private EventPublisherPort eventPublisherPort;

    @InjectMocks
    private CreateProjectUseCase createProjectUseCase;

    @Test
    void testSaveProject_Success() {
        Project project = new Project(
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

        when(companyRepositoryPort.findCompanyByNit(anyLong()))
                .thenReturn(Optional.of(new Company(900123456L, "Test Company", 
                        new co.edu.unicauca.microserviceproject.domain.model.company.VO.Email("test@company.com"))));

        when(projectRepositoryPort.saveProject(any(Project.class)))
                .thenReturn(project);

        Project savedProject = createProjectUseCase.saveProject(project);

        assertNotNull(savedProject);
        verify(companyRepositoryPort, times(1)).findCompanyByNit(anyLong());
        verify(projectRepositoryPort, times(1)).saveProject(any(Project.class));
        verify(eventPublisherPort, times(1)).publishProjectCreatedEvent(any(ProjectCreatedEvent.class));
    }

    @Test
    void testSaveProject_CompanyNotFound() {
        Project project = new Project(
                new RecibidoState(),
                999999999L, // NIT que no existe
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

        when(companyRepositoryPort.findCompanyByNit(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            createProjectUseCase.saveProject(project);
        });
    }
}