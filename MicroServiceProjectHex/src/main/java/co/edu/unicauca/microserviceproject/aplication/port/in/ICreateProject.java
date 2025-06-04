package co.edu.unicauca.microserviceproject.aplication.port.in;

import co.edu.unicauca.microserviceproject.domain.model.project.Project;


public interface ICreateProject {
    Project saveProject(Project project);

}
