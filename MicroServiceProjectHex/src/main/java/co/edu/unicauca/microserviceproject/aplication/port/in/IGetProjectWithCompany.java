package co.edu.unicauca.microserviceproject.aplication.port.in;

import co.edu.unicauca.microserviceproject.infra.dto.ProjectDto;

public interface IGetProjectWithCompany {
    ProjectDto getProjectWithCompany(Long projectId);
}
