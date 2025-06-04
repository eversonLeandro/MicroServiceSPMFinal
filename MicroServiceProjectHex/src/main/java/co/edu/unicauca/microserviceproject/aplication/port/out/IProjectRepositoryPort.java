package co.edu.unicauca.microserviceproject.aplication.port.out;

import co.edu.unicauca.microserviceproject.domain.model.project.Project;

import java.util.List;

/**
 * Puerto de salida
 */
public interface IProjectRepositoryPort {
    Project findProjectByProjectId(Long ide);
    Project saveProject(Project project);
    List<Project> getAllProjects();
    List<Project> GetProjectsByCompany(Long ide);
    Project UpdateStateProject(Project project);
}
