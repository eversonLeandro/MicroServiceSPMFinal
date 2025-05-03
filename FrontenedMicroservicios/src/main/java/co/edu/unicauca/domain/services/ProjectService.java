/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.domain.services;

import co.edu.unicauca.access.ProjectRepository;
import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.infra.dto.ProjectStatusRequest;
import co.edu.unicauca.infra.dto.ProjectStatusResponse;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Yisus
 */
public class ProjectService  {
    private ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }
    public List<Project> listarProyectos() {
        List<Object> objects = repository.list();
        List<Project> projects = new ArrayList<>();
        if (objects == null) {
        System.err.println("No se pudo obtener la lista de proyectos (null).");
        return new ArrayList<>(); // Devuelve lista vacía, no null
    }
        
        for (Object obj : objects) {
            if (obj instanceof Project) {
                projects.add((Project) obj);
            }
        }
       // notificarObservadores(projects);
        // Devuelves la lista de Project como List<Object>
        return projects;
    }
    public Project getProject(Object idproject){
       return repository.found(idproject); 
    }
    
   public List<Project> obtenerProyectosPorNit(String nit) {

        List<Object> objects = repository.getProjectsNit(nit);
        List<Project> projects = new ArrayList<>();
        for (Object obj : objects) {
            if (obj instanceof Project) {
                projects.add((Project) obj);
            }
        }
        // Devuelves la lista de Project como List<Object>
        return new ArrayList<>(projects);

    }
   public boolean saveProject(Project project) {

        return repository.save(project);

    }
   public ProjectStatusResponse updateProjectStatus(ProjectStatusRequest request) {
       
        return repository.actualizarEstado(request);

    }
   
   
}
