package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.in.IGetAllProjects;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICompanyRepositoryPort;
import co.edu.unicauca.microserviceproject.aplication.port.out.IProjectRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.ProjectWithCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GetAllProjectsUseCase implements IGetAllProjects {
    private final IProjectRepositoryPort iprojectRepositoryPort;
    private final ICompanyRepositoryPort icompanyRepositoryPort;

    @Override
    public List<ProjectWithCompany> getAllProjects() {
        List<Project> projects = iprojectRepositoryPort.getAllProjects();
        List<Company> companies = icompanyRepositoryPort.findAllCompanies();

        Map<Long, Company> companyMap = companies.stream()
                .collect(Collectors.toMap(Company::getNit, Function.identity()));


        List<ProjectWithCompany> projectWithCompanies = projects.stream()
                .map(project -> {
                    Company company = companyMap.get(project.getCompanyId());
                    ProjectWithCompany pwc = new ProjectWithCompany(project, company);
                    return pwc;
                })
                .collect(Collectors.toList());
        return projectWithCompanies;

    }

}
