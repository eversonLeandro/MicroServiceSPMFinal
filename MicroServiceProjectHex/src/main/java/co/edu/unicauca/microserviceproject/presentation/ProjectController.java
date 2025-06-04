package co.edu.unicauca.microserviceproject.presentation;

import co.edu.unicauca.microserviceproject.aplication.port.in.*;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.infra.dto.*;
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


    @PostMapping("/project")
    public ResponseEntity<ProjectDto> save(@RequestBody ProjectRequest request) {
        Project project = projectRequestMapper.fromProjectRequestToProject(request);
        Project savedProject = iCreateProject.saveProject(project);
        ProjectDto responseDto = ProjectMapper.domainToDto(savedProject);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id) throws Exception {
        Project projectMapeado = iProjectFindById.findProjectByProjectId(id);
        ProjectDto responseDto = ProjectMapper.domainToDto(projectMapeado);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/projects")

    public ResponseEntity<?> getAllProjects() throws Exception {

        List<Project> projects = iGetAllProjects.getAllProjects();
        List<ProjectDto> responseDto = projects.stream().map(ProjectMapper :: domainToDto).collect(Collectors.toList());
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/projectsCompany/{nit}")
    public ResponseEntity<?> getAllProjectsCompany(@PathVariable Long nit) throws Exception {
        List<Project> projects = iGetProjectsByCompany.GetProjectsByCompany(nit);
        List<ProjectDto> responseDto = projects.stream().map(ProjectMapper :: domainToDto).collect(Collectors.toList());
        return ResponseEntity.ok(projects);
    }
    @PutMapping("/projects/status")
    public ResponseEntity<?> updateStatus(@RequestBody ProjectStatusRequest statusRequest) {
        ProjectStatusResponse response = null;
        try {
            response =  iUpdateProjectStatus.updateProjectStatus(statusRequest.getProjectId(), statusRequest.getAction());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {

            response.setMensaje("{\"error\":\"Error al actualizar datos.\"}");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
