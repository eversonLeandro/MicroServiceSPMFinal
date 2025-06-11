package co.edu.unicauca.microserviceproject.adapter.out;

import co.edu.unicauca.microserviceproject.adapter.out.repository.ProjectJpaRepository;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.FechaEntrega;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.PeriodoAcademico;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.Presupuesto;
import co.edu.unicauca.microserviceproject.domain.model.project.state.RecibidoState;
import co.edu.unicauca.microserviceproject.infra.entities.ProjectEntity;
import co.edu.unicauca.microserviceproject.infra.mappers.ProjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectRepositoryAdapterTest {

    @Mock
    private ProjectJpaRepository projectJpaRepository;

    @InjectMocks
    private ProjectRepositoryAdapter projectRepositoryAdapter;

    private Project project;
    private ProjectEntity entity;

    @BeforeEach
    void setUp() {
        project = new Project(
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

        entity = new ProjectEntity();
        entity.setProjectId(1L);
        entity.setCompanyId(900123456L);
        entity.setCoordinatorId(123L);
        entity.setNombre("Test Project");
        entity.setResumen("Resumen");
        entity.setDescripcion("Descripción");
        entity.setObjetivo("Objetivo");
        entity.setTiempoMaximo("6 meses");
        entity.setPresupuesto("1000000");
        entity.setFechaEntregadaEsperada(LocalDate.now().plusMonths(6).toString());
        entity.setEstado("RECIBIDO");
        entity.setPeriodoAcademico("2025-1");
    }

    @Test
    void testSaveProject() {
        try (MockedStatic<ProjectMapper> mocked = Mockito.mockStatic(ProjectMapper.class)) {
            mocked.when(() -> ProjectMapper.domainToEntity(project)).thenReturn(entity);
            when(projectJpaRepository.save(entity)).thenReturn(entity);
            mocked.when(() -> ProjectMapper.entityToDomain(entity)).thenReturn(project);

            Project savedProject = projectRepositoryAdapter.saveProject(project);

            assertNotNull(savedProject);
            assertEquals("Test Project", savedProject.getNombre());
            mocked.verify(() -> ProjectMapper.domainToEntity(project));
            verify(projectJpaRepository).save(entity);
            mocked.verify(() -> ProjectMapper.entityToDomain(entity));
        }
    }

    @Test
    void testFindProjectByProjectId() {
        try (MockedStatic<ProjectMapper> mocked = Mockito.mockStatic(ProjectMapper.class)) {
            when(projectJpaRepository.findProjectByProjectId(1L)).thenReturn(entity);
            mocked.when(() -> ProjectMapper.entityToDomain(entity)).thenReturn(project);

            Project result = projectRepositoryAdapter.findProjectByProjectId(1L);

            assertNotNull(result);
            assertEquals(900123456L, result.getCompanyId());
            verify(projectJpaRepository).findProjectByProjectId(1L);
            mocked.verify(() -> ProjectMapper.entityToDomain(entity));
        }
    }

    @Test
    void testGetAllProjects() {
        try (MockedStatic<ProjectMapper> mocked = Mockito.mockStatic(ProjectMapper.class)) {
            List<ProjectEntity> entities = List.of(entity);
            when(projectJpaRepository.findAll()).thenReturn(entities);
            mocked.when(() -> ProjectMapper.entityToDomain(entity)).thenReturn(project);

            List<Project> result = projectRepositoryAdapter.getAllProjects();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("Test Project", result.get(0).getNombre());
            verify(projectJpaRepository).findAll();
            mocked.verify(() -> ProjectMapper.entityToDomain(entity));
        }
    }
}