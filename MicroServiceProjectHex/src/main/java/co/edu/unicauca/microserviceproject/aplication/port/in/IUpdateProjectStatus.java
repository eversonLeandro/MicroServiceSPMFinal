package co.edu.unicauca.microserviceproject.aplication.port.in;

import co.edu.unicauca.microserviceproject.infra.dto.ProjectStatusResponse;

public interface IUpdateProjectStatus {
    ProjectStatusResponse updateProjectStatus(Long projectId, String action);
}
