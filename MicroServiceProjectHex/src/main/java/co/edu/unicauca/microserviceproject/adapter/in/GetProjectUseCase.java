package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.in.IProjectFindById;
import co.edu.unicauca.microserviceproject.aplication.port.out.IProjectRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
/**
 *  # Casos de uso (implementan puertos de entrada)
 */
@RequiredArgsConstructor
@Component
public class GetProjectUseCase implements IProjectFindById {


    private final IProjectRepositoryPort projectRepositoryPort;

    @Override
    public Project findProjectByProjectId(Long ide) {
        return projectRepositoryPort.findProjectByProjectId(ide);
    }
}
