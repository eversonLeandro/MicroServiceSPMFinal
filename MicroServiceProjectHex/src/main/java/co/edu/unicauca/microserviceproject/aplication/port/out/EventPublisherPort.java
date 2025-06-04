package co.edu.unicauca.microserviceproject.aplication.port.out;

import co.edu.unicauca.microserviceproject.infra.dto.NotificationStatus;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectCreatedEvent;

public interface EventPublisherPort {
    void publishProjectCreatedEvent(ProjectCreatedEvent project);
    void publishNotificationStatusEvent(NotificationStatus notificationStatus);
}

