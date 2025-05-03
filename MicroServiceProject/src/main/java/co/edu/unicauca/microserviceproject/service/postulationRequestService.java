package co.edu.unicauca.microserviceproject.service;

import co.edu.unicauca.microserviceproject.entities.Postulation;
import co.edu.unicauca.microserviceproject.entities.Project;
import co.edu.unicauca.microserviceproject.repository.PostulationRepository;
import co.edu.unicauca.microserviceproject.repository.ProjectRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class postulationRequestService {

    @Autowired
    private PostulationRepository postulationRepository;

    @RabbitListener(queues = "postulationQueue")
    public void receiveMessage(Postulation postulation) {
        try{

            postulationRepository.save(postulation);
            System.out.println("Postulation saved with rabbitMQ");

        }catch (Exception e){
            System.out.println("Error:" + e.getMessage());
        }

    }
}
