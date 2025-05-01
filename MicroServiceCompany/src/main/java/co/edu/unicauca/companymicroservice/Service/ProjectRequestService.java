package co.edu.unicauca.companymicroservice.Service;

import co.edu.unicauca.companymicroservice.Entities.Project;
import co.edu.unicauca.companymicroservice.Repositories.ProjectRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectRequestService {
    @Autowired
    private ProjectRepository projectRepository;

    @RabbitListener(queues = "projectQueue")
    public void receiveMessage(Project project) {
        try{
            projectRepository.save(project);
            System.out.println("project saved with rabbitMQ");

        }catch (Exception e){
            System.out.println("Error:" + e.getMessage());
        }

    }
}
