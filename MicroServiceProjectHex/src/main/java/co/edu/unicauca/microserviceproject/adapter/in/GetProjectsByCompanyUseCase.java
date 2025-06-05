package co.edu.unicauca.microserviceproject.adapter.in;


import co.edu.unicauca.microserviceproject.aplication.port.in.IGetProjectsByCompany;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICompanyRepositoryPort;
import co.edu.unicauca.microserviceproject.aplication.port.out.IProjectRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.ProjectWithCompany;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectDto;
import co.edu.unicauca.microserviceproject.infra.mappers.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GetProjectsByCompanyUseCase implements IGetProjectsByCompany {


    private final IProjectRepositoryPort iProjectRepositoryPort;
    private final ICompanyRepositoryPort icompanyRepositoryPort;


    @Override
    public List<ProjectDto> getAllProjectsWithCompany(Long nit) {
        List<Project> projects = iProjectRepositoryPort.GetProjectsByCompany(nit);
        Optional<Company> company = icompanyRepositoryPort.findCompanyByNit(nit);

        return projects.stream()
                .map(project -> {
                    ProjectWithCompany pwc = new ProjectWithCompany(project,company.get());
                    return ProjectMapper.projectWithCompanyToProjectDto(pwc);
                })
                .collect(Collectors.toList());
    }
}
