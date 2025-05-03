package co.edu.unicauca.microserviceproject.service;


import co.edu.unicauca.microserviceproject.entities.Company;
import co.edu.unicauca.microserviceproject.entities.Project;
import co.edu.unicauca.microserviceproject.infra.config.RabbitMQConfig;
import co.edu.unicauca.microserviceproject.repository.CompanyRepository;
import co.edu.unicauca.microserviceproject.repository.ProjectRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectConsumerService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @RabbitListener(queues = "projectQueue")
    public void receiveMessage(Project project) {
        try{
            projectRepository.save(project);
            System.out.println("Project saved with rabbitMQ");

        }catch (Exception e){
            System.out.println("Error:" + e.getMessage());
        }

    }
    @RabbitListener(queues = RabbitMQConfig.COMPANY_QUEUE)
    public void receiveMessage2(Company com) {
        try{
            companyRepository.save(com);
            System.out.println("Company saved with rabbitMQ");

        }catch (Exception e){
            System.out.println("Error:" + e.getMessage());
        }

    }

}
