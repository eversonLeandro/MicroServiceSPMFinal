/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.domain.services;

import co.edu.unicauca.access.ProjectRepository;
import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.infra.Messages;
import co.edu.unicauca.infra.adapter.ProjectRepositoryAdapter;
import co.edu.unicauca.infra.adapter.ProjectRequestCompany;
import co.edu.unicauca.infra.Subject;
import co.edu.unicauca.infra.dto.ProjectStatusRequest;
import co.edu.unicauca.infra.dto.ProjectStatusResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Yisus
 */

public class ProjectService extends Subject {

    private ProjectRepository repository;
    private ProjectRepositoryAdapter reposadapter;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
        this.reposadapter = new ProjectRepositoryAdapter(repository);
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

        notificarObservadores(projects);
        // Devuelves la lista de Project como List<Object>
        return projects;
    }

    public Project getProject(Object idproject) {
        return repository.found(idproject);
    }


    public List<Project> obtenerProyectosPorNit(String nit) {

        return reposadapter.getProjectsNit(nit);
    }


    public boolean saveProject(Project project) {

        
        return repository.save(project);

    }

    public ProjectStatusResponse updateProjectStatus(ProjectStatusRequest request) {

        return repository.actualizarEstado(request);

    }


    public List<Map<String, String>> getCommentsByProject(int projectId) {
        try {
            return repository.getCommentsByProject(projectId);
        } catch (IOException e) {
            Messages.showMessageDialog("Error al cargar comentarios: " + e.getMessage(), "Error");
            return List.of();
        }
    }

    public boolean addComment(long projectId, long coordinatorId,
            String coordinatorName, String message) {
        try {
            if (projectId > Integer.MAX_VALUE || projectId < Integer.MIN_VALUE
                    || coordinatorId > Integer.MAX_VALUE || coordinatorId < Integer.MIN_VALUE) {
                Messages.showMessageDialog("ID demasiado grande para ser procesado", "Error");
                return false;
            }

            return repository.addComment((int) projectId, (int) coordinatorId, coordinatorName, message);
        } catch (IOException e) {
            Messages.showMessageDialog("Error al enviar comentario: " + e.getMessage(), "Error");
            return false;
        }
    }


}
