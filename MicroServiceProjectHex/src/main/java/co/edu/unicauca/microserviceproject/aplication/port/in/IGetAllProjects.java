package co.edu.unicauca.microserviceproject.aplication.port.in;

import co.edu.unicauca.microserviceproject.domain.model.project.ProjectWithCompany;

import java.util.List;

/**
 * Puerto de entrada para obtner todos los projectos
 */
public interface IGetAllProjects {
    List<ProjectWithCompany> getAllProjects();
}
