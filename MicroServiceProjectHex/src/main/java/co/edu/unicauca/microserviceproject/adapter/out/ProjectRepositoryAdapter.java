package co.edu.unicauca.microserviceproject.adapter.out;

import co.edu.unicauca.microserviceproject.adapter.out.repository.ProjectJpaRepository;
import co.edu.unicauca.microserviceproject.aplication.port.out.IProjectRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.infra.entities.ProjectEntity;
import co.edu.unicauca.microserviceproject.infra.mappers.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ProjectRepositoryAdapter implements IProjectRepositoryPort {

    @Autowired
    private ProjectJpaRepository projectJpaRepository;

    @Override
    public Project findProjectByProjectId(Long ide) {
        ProjectEntity projectEntity= projectJpaRepository.findProjectByProjectId(ide);
        return ProjectMapper.entityToDomain(projectEntity);
    }

    @Override
    public Project saveProject(Project project) {

        ProjectEntity entity = ProjectMapper.domainToEntity(project);
        ProjectEntity projectEntity= projectJpaRepository.save(entity);
        return ProjectMapper.entityToDomain(projectEntity);

    }

    @Override
    public List<Project> getAllProjects() {
        List<ProjectEntity> projectEntities = projectJpaRepository.findAll();

        return projectEntities.stream().map(ProjectMapper::entityToDomain).collect(Collectors.toList());
    }

    @Override
    public List<Project> GetProjectsByCompany(Long ide) {
        List<ProjectEntity> projectEntities=  projectJpaRepository.getAllByCompany_Nit(ide);
        return projectEntities.stream().map(ProjectMapper::entityToDomain).collect(Collectors.toList());
    }
    @Override
    public Project UpdateStateProject(Project project){
        return null;
    }
}
