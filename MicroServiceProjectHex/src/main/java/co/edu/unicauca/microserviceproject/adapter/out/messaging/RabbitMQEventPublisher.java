package co.edu.unicauca.microserviceproject.adapter.out.messaging;

import co.edu.unicauca.microserviceproject.aplication.port.out.EventPublisherPort;
import co.edu.unicauca.microserviceproject.infra.config.RabbitMQConfig;
import co.edu.unicauca.microserviceproject.infra.dto.NotificationStatus;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class RabbitMQEventPublisher implements EventPublisherPort {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void publishProjectCreatedEvent(ProjectCreatedEvent dto) {
        try {
            rabbitTemplate.convertAndSend(RabbitMQConfig.PROJECT_QUEUE, dto);
        } catch (AmqpException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void publishNotificationStatusEvent(NotificationStatus notificationStatus) {
        try {
            rabbitTemplate.convertAndSend(RabbitMQConfig.PROJECT_STATUS_NOTIFICATION_QUEUE, notificationStatus);
        } catch (AmqpException e) {
            System.out.println(e.getMessage());
        }
    }
}
