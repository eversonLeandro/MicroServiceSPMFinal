package co.edu.unicauca.microserviceproject.controller;
import co.edu.unicauca.microserviceproject.infra.dto.*;
import co.edu.unicauca.microserviceproject.entities.Project;
import co.edu.unicauca.microserviceproject.service.SenderService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.microserviceproject.service.ProjectService;
import java.util.List;

@RestController
@RequestMapping("/apiProject")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    private SenderService senderService;
    @Autowired
    private ProjectMapperCompany projectMapperCompany;


    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id) throws Exception  {
        try {
            Project project = projectService.findById(id);
            if (project == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(project);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error al recuperar datos.\"}");
        }
    }


    @GetMapping("/projects")
    public ResponseEntity<?> getAllProjects() throws Exception  {
        try {
            List<Project> projects = projectService.findAll();
            if (projects == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(projects);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error al recuperar datos.\"}");
        }
    }

    @GetMapping("/projectsCompany/{nit}")
    public ResponseEntity<?> getAllProjectsCompany(@PathVariable Long nit) throws Exception  {
        try{
            List<ProjectRequestCompany> projects = projectService.findAllCompany(nit);
            if (projects == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(projects);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error al recuperar datos.\"}");
        }
    }


    @PostMapping("/project")
    public ResponseEntity<?> createProject(@RequestBody ProjectRequest projectRequest) throws Exception {
        try {
            Project savedProject = projectService.createProject(projectRequest);
            return ResponseEntity.ok(savedProject);
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al guardar datos.\"}");
        }
    }

    @PutMapping("/projects/status")
    public ResponseEntity<?> updateStatus(@RequestBody ProjectStatusRequest statusRequest) {
        ProjectStatusResponse response = null;
        try {
            response = projectService.updateProjectStatus(statusRequest.getProjectId(), statusRequest.getAction());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMensaje("{\"error\":\"Error al actualizar datos.\"}");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


}
