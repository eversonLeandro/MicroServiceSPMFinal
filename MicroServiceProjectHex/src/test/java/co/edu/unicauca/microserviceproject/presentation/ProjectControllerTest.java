package co.edu.unicauca.microserviceproject.presentation;

import co.edu.unicauca.microserviceproject.infra.dto.*;
import co.edu.unicauca.microserviceproject.aplication.port.in.*;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.company.VO.Email;
import co.edu.unicauca.microserviceproject.domain.model.project.Comment;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.ProjectWithCompany;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.FechaEntrega;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.PeriodoAcademico;
import co.edu.unicauca.microserviceproject.domain.model.project.VO.Presupuesto;
import co.edu.unicauca.microserviceproject.domain.model.project.state.RecibidoState;
import co.edu.unicauca.microserviceproject.infra.dto.*;
import co.edu.unicauca.microserviceproject.infra.mappers.CommentMapper;
import co.edu.unicauca.microserviceproject.infra.mappers.ProjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    @Mock
    private ICreateProject iCreateProject;
    @Mock
    private IGetAllProjects iGetAllProjects;
    @Mock
    private IProjectFindById iProjectFindById;
    @Mock
    private IGetProjectsByCompany iGetProjectsByCompany;
    @Mock
    private IUpdateProjectStatus iUpdateProjectStatus;
    @Mock
    private ProjectRequestMapper projectRequestMapper;
    @Mock
    private IAgregateComment iAgregateComment;
    @Mock
    private IGetAllCommentsByCoordinator iGetAllCommentsByCoordinator;
    @Mock
    private IGetProjectWithCompany iGetProjectWithCompany;
    @Mock
    private IGetCommentByProject iGetCommentByProject;

    @InjectMocks
    private ProjectController projectController;

    private Project project;
    private ProjectDto projectDto;
    private Comment comment;
    private CommentRequest commentRequest;

    @BeforeEach
    void init() {
        comment = new Comment(1, 100, 200, "Coord", "Test Message", LocalDateTime.now());
        commentRequest = new CommentRequest(200, "Coord", "Test Message");

        project = new Project(
                1L,
                new RecibidoState(),
                1L,
                1L,
                "Test Project",
                "Test Resumen",
                "Test Descripcion",
                "Test Objetivo",
                "6 meses",
                Presupuesto.of(new BigDecimal("1000000")),
                FechaEntrega.of(LocalDate.of(2027, 12, 30)),
                PeriodoAcademico.of("2023-2")
        );

        projectDto = new ProjectDto(
                1L,
                1L,
                "Test Project",
                "Test Resumen",
                "Test Descripcion",
                "Test Objetivo",
                "6 meses",
                "1000000",
                "30/12/2023",
                "RECIBIDO",
                "2023-2",
                "Test Company"
        );

        Company company = new Company(1L, "Test Company", new Email("test@company.com"));
    }

    @Test
    void testSaveProject() {
        ProjectRequest request = ProjectRequest.builder()
                .nitCompany(1L)
                .coordinatorId(1L)
                .nombre("Test Project")
                .resumen("Test Resumen")
                .descripcion("Test Descripcion")
                .objetivo("Test Objetivo")
                .TiempoMaximo("6 meses")
                .presupuesto("1000000")
                .FechaEntregadaEsperada("30/12/2027")
                .periodoAcademico("2023-2")
                .build();

        when(projectRequestMapper.fromProjectRequestToProject(any())).thenReturn(project);
        when(iCreateProject.saveProject(any())).thenReturn(project);

        try (MockedStatic<ProjectMapper> mocked = mockStatic(ProjectMapper.class)) {
            mocked.when(() -> ProjectMapper.domainToDto(any())).thenReturn(projectDto);

            ResponseEntity<ProjectDto> response = projectController.save(request);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            verify(iCreateProject).saveProject(project);
        }
    }


    @Test
    void testGetProject() throws Exception {
        when(iGetProjectWithCompany.getProjectWithCompany(1L)).thenReturn(projectDto);

        ResponseEntity<?> response = projectController.getProject(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof ProjectDto);
    }

    @Test
    void testGetAllProjects() throws Exception {
        when(iGetAllProjects.getAllProjects()).thenReturn(List.of());

        ResponseEntity<?> response = projectController.getAllProjects();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
    }

    @Test
    void testGetAllProjectsCompany() throws Exception {
        when(iGetProjectsByCompany.getAllProjectsWithCompany(1L)).thenReturn(List.of(projectDto));

        ResponseEntity<?> response = projectController.getAllProjectsCompany(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
    }

    @Test
    void testUpdateStatus_WithObjectMapper() throws Exception {
        String json = """
    {
        "projectId": 1,
        "action": "ACEPTADO"
    }
    """;


        ObjectMapper mapper = new ObjectMapper();
        ProjectStatusRequest request = mapper.readValue(json, ProjectStatusRequest.class);

        ProjectStatusResponse statusResponse = new ProjectStatusResponse("ACEPTADO", "Proyecto aceptado");
        when(iUpdateProjectStatus.updateProjectStatus(1L, "ACEPTADO")).thenReturn(statusResponse);

        ResponseEntity<?> response = projectController.updateStatus(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof ProjectStatusResponse);
        assertEquals("Proyecto aceptado", ((ProjectStatusResponse) response.getBody()).getMensaje());
    }

    @Test
    void testGetComments() {
        when(iGetCommentByProject.GetCommentByProject(100)).thenReturn(List.of(comment));

        ResponseEntity<?> response = projectController.getComments(100);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
    }

    @Test
    void testAddComment() {
        when(iAgregateComment.AgregateComment(any())).thenReturn(comment);

        try (MockedStatic<CommentMapper> mocked = mockStatic(CommentMapper.class)) {
            mocked.when(() -> CommentMapper.crear(anyInt(), any())).thenReturn(comment);
            mocked.when(() -> CommentMapper.domainToDto(any())).thenReturn(commentRequest);

            ResponseEntity<CommentRequest> response = projectController.addComment(100, commentRequest);

            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertEquals("Test Message", response.getBody().getMessage());
        }
    }


    @Test
    void testGetCommentsByCoordinator() {
        when(iGetAllCommentsByCoordinator.getAllComments(100, 200)).thenReturn(List.of(comment));

        ResponseEntity<?> response = projectController.getCommentsByCoordinator(100, 200);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
    }
} 
