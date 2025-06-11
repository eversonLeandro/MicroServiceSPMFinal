package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.out.ICompanyRepositoryPort;
import co.edu.unicauca.microserviceproject.aplication.port.out.IProjectRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.ProjectWithCompany;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.*;
import co.edu.unicauca.microserviceproject.domain.model.project.state.RecibidoState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllProjectsUseCaseTest {

    @Mock
    private IProjectRepositoryPort projectRepositoryPort;

    @Mock
    private ICompanyRepositoryPort companyRepositoryPort;

    @InjectMocks
    private GetAllProjectsUseCase getAllProjectsUseCase;

    @Test
    void testGetAllProjects() {
        Project project1 = new Project(
                1L,
                new RecibidoState(),
                900123456L,
                123L,
                "Project 1",
                "Resumen 1",
                "Descripción 1",
                "Objetivo 1",
                "6 meses",
                Presupuesto.of(new BigDecimal("1000000")),
                FechaEntrega.of(LocalDate.now().plusMonths(6)),
                PeriodoAcademico.of("2025-1")
        );

        Project project2 = new Project(
                2L,
                new RecibidoState(),
                900123456L,
                123L,
                "Project 2",
                "Resumen 2",
                "Descripción 2",
                "Objetivo 2",
                "6 meses",
                Presupuesto.of(new BigDecimal("2000000")),
                FechaEntrega.of(LocalDate.now().plusMonths(6)),
                PeriodoAcademico.of("2025-1")
        );

        Company company = new Company(
                900123456L,
                "Test Company",
                new co.edu.unicauca.microserviceproject.domain.model.company.VO.Email("test@company.com")
        );

        when(projectRepositoryPort.getAllProjects())
                .thenReturn(List.of(project1, project2));

        when(companyRepositoryPort.findAllCompanies())
                .thenReturn(List.of(company));

        List<ProjectWithCompany> result = getAllProjectsUseCase.getAllProjects();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Project 1", result.get(0).getProject().getNombre());
        assertEquals("Test Company", result.get(0).getCompany().getNombre());
    }
}