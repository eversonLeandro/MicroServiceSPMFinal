package co.edu.unicauca.microserviceproject.service;

import co.edu.unicauca.microserviceproject.entities.Project;
import co.edu.unicauca.microserviceproject.infra.config.RabbitMQConfig;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectMapperCompany;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectRequestCompany;
import jakarta.transaction.Transactional;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SenderService {
    private static final Logger logger = LoggerFactory.getLogger(SenderService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ProjectMapperCompany projectMapperCompany;

    @Async
    public void sendProject(ProjectRequestCompany dto) {
        try {
            logger.info("📤 Enviando proyecto a RabbitMQ: {}", dto);
            rabbitTemplate.convertAndSend(RabbitMQConfig.PROJECT_QUEUE, dto);
        } catch (AmqpException e) {
            logger.error("⚠️ Error al enviar proyecto a RabbitMQ. Intentando reconexión...", e);
            // Intento de reconexión
            try {
                rabbitTemplate.convertAndSend(RabbitMQConfig.PROJECT_QUEUE, dto);
                logger.info("✅ Reconexión exitosa, mensaje enviado");
            } catch (AmqpException ex) {
                logger.error("❌ Error crítico al enviar proyecto después de reintento", ex);
                throw new MessagingException("Error al enviar proyecto a RabbitMQ", ex);
            }
        }
    }

    public void sendMessage(String queueName, Object message) {
        logger.info("📤 Enviando mensaje a la cola: {} → {}", queueName, message);
        try {
            rabbitTemplate.convertAndSend(queueName, message);
        } catch (AmqpException e) {
            logger.error("⚠️ Error al enviar mensaje a RabbitMQ", e);
            throw e; // Puedes personalizar esta excepción según tus necesidades
        }
    }
}