package co.edu.unicauca.microserviceproject.aplication.port.in;

import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.ProjectWithCompany;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectDto;

import java.util.List;

public interface IGetProjectsByCompany {
    List<ProjectDto> getAllProjectsWithCompany(Long nit);
}
