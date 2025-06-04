package co.edu.unicauca.companymicroservice.Service;

import co.edu.unicauca.companymicroservice.Entities.Company;
import co.edu.unicauca.companymicroservice.Entities.Project;
import co.edu.unicauca.companymicroservice.Infra.DTO.ProjectRequestCompany;
import co.edu.unicauca.companymicroservice.Infra.Mappers.ProjectMapper;
import co.edu.unicauca.companymicroservice.Repositories.CompanyRepository;
import co.edu.unicauca.companymicroservice.Infra.Config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProjectConsumerService {
    @Autowired
    private CompanyRepository companyRepository ;
    @Autowired
    private CompanyService companyService;
    private ProjectMapper mapperProject = new ProjectMapper();

    @RabbitListener(queues = RabbitMQConfig.PROJECT_QUEUE)
    @Transactional
    public void receiveMessage(ProjectRequestCompany projectdto) {
        try{
            Optional<Company> companyOptional = companyRepository.findById(String.valueOf(projectdto.getNitCompany()));
            if (companyOptional.isPresent()) {
                Company company = companyOptional.get();
                Project project = mapperProject.projectToEntity(projectdto, company);

                company.getProyectos().add(project);
                boolean updated = companyService.update(company.getNit(), company);

                if (updated) {
                    System.out.println("Proyecto '{}' agregado exitosamente a la compañía '{}'");
                } else {
                    System.out.println("No se pudo actualizar la compañía '{}' con el nuevo proyecto");
                }
            } else {
                System.out.println("No se encontró la compañía con NIT: {}");
            }

        }catch (Exception e){
            System.out.println("Error:" + e.getMessage());
        }

    }

}
