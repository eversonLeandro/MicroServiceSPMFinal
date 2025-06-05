package co.edu.unicauca.microserviceproject.aplication.port.in;

import co.edu.unicauca.microserviceproject.infra.dto.ProjectWithCompanyDto;

import java.util.List;

public interface IGetProjectsWithCompany {
    List<ProjectWithCompanyDto> getAll();
}
