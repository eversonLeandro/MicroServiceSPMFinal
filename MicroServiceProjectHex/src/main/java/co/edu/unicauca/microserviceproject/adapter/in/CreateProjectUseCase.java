package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.in.ICreateProject;
import co.edu.unicauca.microserviceproject.aplication.port.out.EventPublisherPort;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICompanyRepositoryPort;
import co.edu.unicauca.microserviceproject.aplication.port.out.IProjectRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectCreatedEvent;
import co.edu.unicauca.microserviceproject.infra.mappers.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CreateProjectUseCase implements ICreateProject {

    private final IProjectRepositoryPort projectRepositoryPort;

    private final ICompanyRepositoryPort companyRepositoryPort;

    private final EventPublisherPort eventPublisherPort;


    @Override
    public Project saveProject(Project project) {
        Company company = companyRepositoryPort.findCompanyByNit(project.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "La empresa con NIT " + project.getCompanyId() + " no existe."
                ));

        

        Project savedProject = projectRepositoryPort.saveProject(project);

        ProjectCreatedEvent event = ProjectMapper.domainToEvent(savedProject);
        eventPublisherPort.publishProjectCreatedEvent(event);

        return savedProject;
    }
}
