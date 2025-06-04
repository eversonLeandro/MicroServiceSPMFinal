package co.edu.unicauca.microserviceproject.aplication.port.in;

import co.edu.unicauca.microserviceproject.domain.model.project.Project;

import java.util.List;

public interface IGetProjectsByCompany {
    List<Project> GetProjectsByCompany(Long nit);
}
