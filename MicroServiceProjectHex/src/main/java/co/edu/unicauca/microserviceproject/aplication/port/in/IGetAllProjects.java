package co.edu.unicauca.microserviceproject.aplication.port.in;

import co.edu.unicauca.microserviceproject.domain.model.project.Project;

import java.util.List;

/**
 * Puerto de entrada para obtner todos los projectos
 */
public interface IGetAllProjects {
    List<Project> getAllProjects();
}
