package co.edu.unicauca.companymicroservice.Service;

import co.edu.unicauca.companymicroservice.Entities.Company;
import co.edu.unicauca.companymicroservice.Entities.Project;
import co.edu.unicauca.companymicroservice.Infra.DTO.ProjectRequestCompany;
import co.edu.unicauca.companymicroservice.Infra.Mappers.ProjectMapper;
import co.edu.unicauca.companymicroservice.Repositories.CompanyRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectConsumerService {
    @Autowired
    private CompanyRepository companyRepository ;
    @Autowired
    private CompanyService companyService;
    private ProjectMapper mapperProject = new ProjectMapper();

    @RabbitListener(queues = "projectQueue")
    public void receiveMessage(ProjectRequestCompany projectdto) {
        try{
            Optional<Company> company = companyRepository.findById(String.valueOf(projectdto.getNitCompany()));
            Project project = mapperProject.projectToEntity(projectdto, company.get());

            company.get().getProyectos().add(project);
            companyService.update(company.get().getNit(), company.get());


        }catch (Exception e){
            System.out.println("Error:" + e.getMessage());
        }

    }

}
