package co.edu.unicauca.microserviceproject.presentation;

import co.edu.unicauca.microserviceproject.aplication.port.in.*;
import co.edu.unicauca.microserviceproject.domain.model.project.Comment;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.ProjectWithCompany;
import co.edu.unicauca.microserviceproject.infra.dto.*;
import co.edu.unicauca.microserviceproject.infra.mappers.CommentMapper;
import co.edu.unicauca.microserviceproject.infra.mappers.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/apiProject")
public class ProjectController {

    //Casos de uso
    @Autowired
    ICreateProject iCreateProject;
    @Autowired
    IGetAllProjects iGetAllProjects;
    @Autowired
    IProjectFindById iProjectFindById;
    @Autowired
    IGetProjectsByCompany iGetProjectsByCompany;
    @Autowired
    IUpdateProjectStatus iUpdateProjectStatus;
    @Autowired
    ProjectRequestMapper projectRequestMapper;
    @Autowired
    IAgregateComment IagregateComment;
    @Autowired
    IGetAllCommentsByCoordinator IgetcommentByCoordiantor;
    @Autowired
    IGetProjectWithCompany iGetProjectWithCompany;


    @Autowired
    IGetCommentByProject IgetCommentByProject;

    @PostMapping("/project")
    public ResponseEntity<ProjectDto> save(@RequestBody ProjectRequest request) {
        Project project = projectRequestMapper.fromProjectRequestToProject(request);
        Project savedProject = iCreateProject.saveProject(project);
        ProjectDto responseDto = ProjectMapper.domainToDto(savedProject);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id) throws Exception {
        ProjectDto responseDto = iGetProjectWithCompany.getProjectWithCompany(id);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/projects")
    public ResponseEntity<?> getAllProjects() throws Exception {
        List<ProjectWithCompany> allProjects = iGetAllProjects.getAllProjects();
        List<ProjectDto> dtos = allProjects.stream().map(ProjectMapper::projectWithCompanyToProjectDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/projectsCompany/{nit}")
    public ResponseEntity<?> getAllProjectsCompany(@PathVariable Long nit) throws Exception {
        List<ProjectDto> responseDto = iGetProjectsByCompany.getAllProjectsWithCompany(nit);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/projects/status")
    public ResponseEntity<?> updateStatus(@RequestBody ProjectStatusRequest statusRequest) {
        ProjectStatusResponse response = new ProjectStatusResponse();
        try {
            response = iUpdateProjectStatus.updateProjectStatus(statusRequest.getProjectId(), statusRequest.getAction());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {

            response.setMensaje("{\"error\":\"Error al actualizar datos.\"}");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /// /
    @GetMapping("/comments/{projectId}")
    public ResponseEntity<?> getComments(@PathVariable Integer projectId) {
        List<Comment> comments = IgetCommentByProject.GetCommentByProject(projectId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/comment/{projectId}")
    public ResponseEntity<CommentRequest> addComment(
            @PathVariable Integer projectId,
            @RequestBody CommentRequest request) {
        Comment comment1 = CommentMapper.crear(projectId, request);
        Comment savedcomment = IagregateComment.AgregateComment(comment1);
        CommentRequest responseDto = CommentMapper.domainToDto(savedcomment);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/commentsCoordinator/{coordinatorId}")
    public ResponseEntity<?> getCommentsByCoordinator(
            @PathVariable Integer projectId,
            @PathVariable Integer coordinatorId) {

        List<Comment> comments = IgetcommentByCoordiantor.getAllComments(projectId, coordinatorId);
        return ResponseEntity.ok(comments);
    }
}
