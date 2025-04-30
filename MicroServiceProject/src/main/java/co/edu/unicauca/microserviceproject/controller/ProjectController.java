package co.edu.unicauca.microserviceproject.controller;

import co.edu.unicauca.microserviceproject.infra.dto.ProjectRequestPostulation;
import co.edu.unicauca.microserviceproject.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.microserviceproject.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/apiPostulation")
public class ProjectController {

    @Autowired
    ProjectService projectService;

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

    @PostMapping("/project")
    public ResponseEntity<?> createProject(@RequestBody ProjectRequestPostulation projectRequestPostulation) throws Exception {

        Project savedProject = null;
        try {
            /*savedProject = projectService.createProject(
                    projectRequestPostulation.getNombre(),
                    projectRequestPostulation.getResumen(),
                    projectRequestPostulation.getDescripcion(),
                    projectRequestPostulation.getObjetivo(),
                    projectRequestPostulation.getTiempoMaximo(),
                    projectRequestPostulation.getPresupuesto(),
                    projectRequestPostulation.getFechaEntregadaEsperada(),
                    projectRequestPostulation.getNitCompany(),
                    projectRequestPostulation.getCodCor(),
                    projectRequestPostulation.getIdPostulaciones()
            );
            */
            savedProject = projectService.createProject(projectRequestPostulation);

        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al guardar datos.\"}");
        }
        return ResponseEntity.ok(savedProject);
    }



}
