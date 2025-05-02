package co.edu.unicauca.microserviceproject.service;

import co.edu.unicauca.microserviceproject.entities.Project;
import co.edu.unicauca.microserviceproject.infra.config.RabbitMQConfig;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectMapperCompany;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectRequestCompany;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SenderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ProjectMapperCompany projectMapperCompany;

    @Async
    public void sendProject(ProjectRequestCompany dto) {
        System.out.println("📤 Enviando proyecto a RabbitMQ: " + dto);
        rabbitTemplate.convertAndSend(RabbitMQConfig.PROJECT_QUEUE, dto);
    }

    // Si quieres un método más general para mandar cualquier mensaje
    public void sendMessage(String queueName, Object message) {
        System.out.println("📤 Enviando mensaje a la cola: " + queueName + " → " + message);
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
