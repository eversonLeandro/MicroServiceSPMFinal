package co.edu.unicauca.microserviceproject.service;

import co.edu.unicauca.microserviceproject.infra.config.RabbitMQConfig;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectMapperCompany;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectRequestCompany;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectRequestPostulation;
import co.edu.unicauca.microserviceproject.entities.Company;
import co.edu.unicauca.microserviceproject.entities.Coordinator;
import co.edu.unicauca.microserviceproject.entities.Postulation;
import co.edu.unicauca.microserviceproject.entities.Project;
import jakarta.transaction.Transactional;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unicauca.microserviceproject.repository.CompanyRepository;
import co.edu.unicauca.microserviceproject.repository.CoordinatorRepository;
import co.edu.unicauca.microserviceproject.repository.PostulationRepository;
import co.edu.unicauca.microserviceproject.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CoordinatorRepository coordinatorRepository;
    @Autowired
    PostulationRepository postulationRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ProjectMapperCompany projectMapperCompany;


    public List<Project> findAll() throws Exception {
        try {
            return projectRepository.findAll();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Project> findAllCompany(Long nit) throws Exception {
        try {
            return projectRepository.findAllByCompany_Nit(nit);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



    public Project findById(Long id) throws Exception {
        return projectRepository.findById(id).get();
    }

    @Transactional
    public Project createProject(ProjectRequestPostulation dto) throws Exception {

        if (dto == null) {
            throw new IllegalArgumentException("El DTO del proyecto no puede ser nulo");
        }

        Project project = new Project();
        project.setNombre(dto.getNombre());
        project.setResumen(dto.getResumen());
        project.setDescripcion(dto.getDescripcion());
        project.setObjetivo(dto.getObjetivo());
        project.setTiempoMaximo(dto.getTiempoMaximo());
        project.setPresupuesto(dto.getPresupuesto());
        project.setFechaEntregadaEsperada(dto.getFechaEntregadaEsperada());


        Optional<Company> company = companyRepository.findById(dto.getNitCompany());
        if (company.isEmpty()) {
            throw new IllegalAccessException("El usuario con ID " + dto.getNitCompany() + " no existe");
        }
        project.setCompany(company.get());

        // Guardar primero en base de datos
        Project savedProject = projectRepository.save(project);

        ProjectRequestCompany projectRequestCompany = projectMapperCompany.dto(project);

        try {
            // Enviar a RabbitMQ
            rabbitTemplate.convertAndSend(RabbitMQConfig.PROJECT_QUEUE, projectRequestCompany);
        } catch (AmqpException e) {
            System.out.println(e.getMessage());
        }
        return savedProject;
    }

    public void deleteById(Long id) throws Exception {
        projectRepository.deleteById(id);
    }


}
