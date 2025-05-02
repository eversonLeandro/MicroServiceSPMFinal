package co.edu.unicauca.microserviceproject.infra.config;


import co.edu.unicauca.microserviceproject.entities.Project;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectMapperCompany;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectRequestCompany;
import co.edu.unicauca.microserviceproject.repository.ProjectRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ProjectEventListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ProjectMapperCompany projectMapperCompany;

    @TransactionalEventListener
    public void handle(ProjectCreatedEvent event) {
        Project project = event.getProject();
        ProjectRequestCompany dto = projectMapperCompany.dto(project);
        System.out.println("si envio");
        rabbitTemplate.convertAndSend(RabbitMQConfig.PROJECT_QUEUE, dto);
    }

}
