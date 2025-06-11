package co.edu.unicauca.microserviceproject.domain.model.project;

import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.FechaEntrega;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.PeriodoAcademico;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.Presupuesto;
import co.edu.unicauca.microserviceproject.domain.model.project.state.RecibidoState;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectWithCompanyTest {

    @Test
    void testProjectWithCompanyCreation() {
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
        Company company = new Company(900123456L, "Test Company", null);
        
        ProjectWithCompany pwc = new ProjectWithCompany(project, company);
        
        assertNotNull(pwc);
        assertEquals(project, pwc.getProject());
        assertEquals(company, pwc.getCompany());
    }
}