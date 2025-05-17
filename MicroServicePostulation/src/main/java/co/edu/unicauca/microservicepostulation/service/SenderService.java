package co.edu.unicauca.microservicepostulation.service;
import co.edu.unicauca.microservicepostulation.dto.PostulationDTO;
import co.edu.unicauca.microservicepostulation.infra.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SenderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendPostulation(PostulationDTO postulationDTO) {
        System.out.println("📤 Enviando postulación a RabbitMQ: " + postulationDTO);
        rabbitTemplate.convertAndSend(RabbitMQConfig.POSTULATION_QUEUE, postulationDTO);
    }

    public void sendMessage(String queueName, Object message) {
        System.out.println("📤 Enviando mensaje a la cola: " + queueName + " → " + message);
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
