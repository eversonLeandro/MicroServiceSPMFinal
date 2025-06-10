package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.in.IUpdateProjectStatus;
import co.edu.unicauca.microserviceproject.aplication.port.out.EventPublisherPort;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICompanyRepositoryPort;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICoordinatorRepositoryPort;
import co.edu.unicauca.microserviceproject.aplication.port.out.IProjectRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.coordinator.Coordinator;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.domain.model.project.state.ProjectState;
import co.edu.unicauca.microserviceproject.infra.dto.NotificationStatus;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UpdateProjectStatusUseCase implements IUpdateProjectStatus {

    private final IProjectRepositoryPort projectRepositoryPort;
    private final ICompanyRepositoryPort companyRepositoryPort;
    private final ICoordinatorRepositoryPort icoordinatorRepositoryPort;

    private final EventPublisherPort eventPublisherPort;

    //private final NotificationPort notificationPort;


    @Override
    public ProjectStatusResponse updateProjectStatus(Long projectId, String action) {

        Project project = projectRepositoryPort.findProjectByProjectId(projectId);

        if (project == null) {
            throw new IllegalArgumentException("Proyecto con ID " + projectId + " no encontrado");
        }

        ProjectState nuevoEstado = switch (action.toLowerCase()) {
            case "avanzar" -> project.getEstado().avanzarEstado(project);
            case "noavanzar" -> project.getEstado().noAvanzaEstado(project);
            default -> throw new IllegalArgumentException("Acción no válida: " + action);
        };

        project.cambiarEstado(nuevoEstado);
        projectRepositoryPort.saveProject(project);

        Company company = companyRepositoryPort.findCompanyByNit(project.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "La empresa con NIT " + project.getCompanyId() + " no existe.")
                );

        Coordinator coordinator = icoordinatorRepositoryPort.findCoordinatorById(project.getCoordinatorId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "La empresa con NIT " + project.getCompanyId() + " no existe.")
                );

        eventPublisherPort.publishNotificationStatusEvent(
                new NotificationStatus(
                        project.getNombre(),
                        nuevoEstado.getEstado(),
                        company.getEmail().getEmail(),
                        coordinator.getEmail().getEmail()
                )
        );

        ProjectStatusResponse response = new ProjectStatusResponse(nuevoEstado.getEstado(), nuevoEstado.getMensaje());
        return response;
    }
}
