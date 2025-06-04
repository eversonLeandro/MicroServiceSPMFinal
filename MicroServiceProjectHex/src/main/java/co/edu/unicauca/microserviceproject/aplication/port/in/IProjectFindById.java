package co.edu.unicauca.microserviceproject.aplication.port.in;

import co.edu.unicauca.microserviceproject.domain.model.project.Project;
/**
 * Puerto de entrada
 */
public interface IProjectFindById {
    Project findProjectByProjectId(Long ide);
}
