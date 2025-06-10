package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.in.*;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.ProjectWithCompany;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectDto;
import co.edu.unicauca.microserviceproject.infra.mappers.ProjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class GetProjectWithCompany implements IGetProjectWithCompany {


    private final IProjectFindById projectFindById;
    private final ICompanyFindByNit getCompanyByNit;

    @Override
    public ProjectDto getProjectWithCompany(Long projectId) {
        Project project = projectFindById.findProjectByProjectId(projectId);
        Company company = getCompanyByNit.getCompanyByNit(project.getCompanyId());

        ProjectWithCompany projectWithCompany = new ProjectWithCompany(project, company);

        return ProjectMapper.projectWithCompanyToProjectDto(projectWithCompany);
    }
}
