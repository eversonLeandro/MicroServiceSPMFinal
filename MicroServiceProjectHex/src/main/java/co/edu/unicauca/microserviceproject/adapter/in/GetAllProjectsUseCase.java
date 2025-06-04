package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.in.IGetAllProjects;
import co.edu.unicauca.microserviceproject.aplication.port.out.IProjectRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
public class GetAllProjectsUseCase implements IGetAllProjects {
    private final IProjectRepositoryPort projectRepositoryPort;
    @Override
    public List<Project> getAllProjects() {
        return projectRepositoryPort.getAllProjects();
    }
}
