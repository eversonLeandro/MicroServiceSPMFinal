package co.edu.unicauca.microserviceproject.adapter.in.messaging;


import co.edu.unicauca.microserviceproject.aplication.port.out.ICompanyRepositoryPort;
import co.edu.unicauca.microserviceproject.aplication.port.out.IPostulationRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.company.VO.Email;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.infra.config.RabbitMQConfig;
import co.edu.unicauca.microserviceproject.infra.dto.CompanyRequestProject;
import co.edu.unicauca.microserviceproject.infra.dto.PostulationDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionsUseCase {
    @Autowired
    private ICompanyRepositoryPort companyRepository;
    @Autowired
    private IPostulationRepositoryPort postulationRepository;

    @RabbitListener(queues = RabbitMQConfig.COMPANY_QUEUE)
    public void receiveMessage2(CompanyRequestProject com) {

        Company c = new Company(com.getNit(),com.getNombre(),new Email(com.getEmail()));
        try{
            companyRepository.saveCompany(c);
            System.out.println("Company saved with rabbitMQ");

        }catch (Exception e){
            System.out.println("Error:" + e.getMessage());
        }

    }
    @RabbitListener(queues = "postulationQueue")
    public void receiveMessage(PostulationDTO postulation) {
        try{
            postulationRepository.savePostulation(postulation);
            System.out.println("Postulation saved with rabbitMQ");

        }catch (Exception e){
            System.out.println("Error:" + e.getMessage());
        }
    }
}
