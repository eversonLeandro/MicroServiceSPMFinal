package co.edu.unicauca.microserviceproject.adapter.out.messaging;

import co.edu.unicauca.microserviceproject.infra.config.RabbitMQConfig;
import co.edu.unicauca.microserviceproject.infra.dto.NotificationStatus;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectCreatedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RabbitMQEventPublisherTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private RabbitMQEventPublisher eventPublisher;

    @Test
    void testPublishProjectCreatedEvent_Success() {
        ProjectCreatedEvent event = new ProjectCreatedEvent(
                1L, 900123456L, "Test Project", "Resumen", 
                "Descripción", "Objetivo", "6 meses", "1000000", 
                "30/12/2025", "RECIBIDO", "2025-1"
        );

        eventPublisher.publishProjectCreatedEvent(event);

        verify(rabbitTemplate, times(1))
                .convertAndSend(RabbitMQConfig.PROJECT_QUEUE, event);
    }

    @Test
    void testPublishProjectCreatedEvent_Error() {
        ProjectCreatedEvent event = new ProjectCreatedEvent(
                1L, 900123456L, "Test Project", "Resumen", 
                "Descripción", "Objetivo", "6 meses", "1000000", 
                "30/12/2025", "RECIBIDO", "2025-1"
        );

        doThrow(new AmqpException("RabbitMQ error"))
                .when(rabbitTemplate).convertAndSend(anyString(), Optional.ofNullable(any()));

        eventPublisher.publishProjectCreatedEvent(event);

        verify(rabbitTemplate, times(1))
                .convertAndSend(RabbitMQConfig.PROJECT_QUEUE, event);
    }

    @Test
    void testPublishNotificationStatusEvent() {
        NotificationStatus notification = new NotificationStatus(
                "Test Project", "ACEPTADO", 
                "company@test.com", "coord@test.com"
        );

        eventPublisher.publishNotificationStatusEvent(notification);

        verify(rabbitTemplate, times(1))
                .convertAndSend(RabbitMQConfig.PROJECT_STATUS_NOTIFICATION_QUEUE, notification);
    }
}