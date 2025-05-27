package co.edu.unicauca.microserviceproject.service;

import co.edu.unicauca.microserviceproject.entities.Coordinator;
import co.edu.unicauca.microserviceproject.infra.Prototype.ProjectPrototypeRegister;
import co.edu.unicauca.microserviceproject.infra.config.RabbitMQConfig;
import co.edu.unicauca.microserviceproject.infra.dto.*;
import co.edu.unicauca.microserviceproject.entities.Company;
import co.edu.unicauca.microserviceproject.entities.Project;
import co.edu.unicauca.microserviceproject.infra.states.MessageResponse;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import co.edu.unicauca.microserviceproject.repository.CompanyRepository;
import co.edu.unicauca.microserviceproject.repository.CoordinatorRepository;
import co.edu.unicauca.microserviceproject.repository.PostulationRepository;
import co.edu.unicauca.microserviceproject.repository.ProjectRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private ProjectPrototypeRegister prototypeRegistry;
    @Autowired
    private SenderService senderService;
    @Autowired
    private ProjectPrototypeRegister projectPrototypeRegister;


    public List<Project> findAll() throws Exception {
        try {
            return projectRepository.findAll();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<ProjectRequestCompany> findAllCompany(Long nit) throws Exception {
        try {
            List<Project> projects = projectRepository.findAllByCompany_Nit(nit);

            return projects.stream()
                    .map(projectMapperCompany::dto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new Exception("Error al obtener los proyectos de la empresa: " + e.getMessage());
        }
    }


    public Project findById(Long id) throws Exception {
        return projectRepository.findById(id).get();
    }


    public Project createProject(ProjectRequest dto) throws Exception {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO del proyecto no puede ser nulo");
        }

        Project project = (Project) projectPrototypeRegister.getGestor().clonar("DEFECTO");
        project.setNombre(dto.getNombre());
        project.setResumen(dto.getResumen());
        project.setDescripcion(dto.getDescripcion());
        project.setObjetivo(dto.getObjetivo());
        project.setTiempoMaximo(dto.getTiempoMaximo());
        project.setPresupuesto(dto.getPresupuesto());
        project.setFechaEntregadaEsperada(dto.getFechaEntregadaEsperada());
        project.setPeriodoAcademico(dto.getPeriodoAcademico());
        project.setEstadoTexto("RECIBIDO");
        project.setPeriodoAcademico(calcularPeriodo());
        Optional<Company> company = companyRepository.findById(dto.getNitCompany());
        if (company.isEmpty()) {
            throw new IllegalArgumentException("La compañía con NIT " + dto.getNitCompany() + " no existe.");
        }
        Optional<Coordinator> coordinator = coordinatorRepository.findById(Long.valueOf("123"));
        project.setCoordinator(coordinator.get());

        project.setCompany(company.get());
        Project savedProject = projectRepository.save(project);

        ProjectRequestCompany projectRequestCompany = projectMapperCompany.dto(savedProject);

//        try {
//            // Enviar a RabbitMQ
//            rabbitTemplate.convertAndSend(RabbitMQConfig.PROJECT_QUEUE, projectRequestCompany);
//        } catch (AmqpException e) {
//            System.out.println(e.getMessage());
//        }


        // Preparar llamada REST
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProjectRequestCompany> request = new HttpEntity<>(projectRequestCompany, headers);

        ResponseEntity<ProjectRequestCompany> response = restTemplate.postForEntity(
                "http://localhost:8088/apiCompanies/saveProject",
                request,
                ProjectRequestCompany.class
        );

        ProjectRequestCompany responseBody = response.getBody();
        System.out.println("Respuesta del microservicio: " + responseBody);

        return savedProject;
    }

    public void deleteById(Long id) throws Exception {
        projectRepository.deleteById(id);
    }

    @Transactional
    public ProjectStatusResponse updateProjectStatus(Long projectId, String action) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        MessageResponse messageResponse = null;

        if ("avanzar".equalsIgnoreCase(action)) {
            messageResponse = project.getEstado().avanzarEstado(project);

        } else if ("noAvanzar".equalsIgnoreCase(action)) {
            messageResponse = project.getEstado().NoAvanzaEstado(project);
        } else {
            throw new IllegalArgumentException("Acción no válida: " + action);
        }
        project.setEstado(messageResponse.getEstado());
        projectRepository.save(project);

        NotificationStatus notificationDTO = new NotificationStatus(
                project.getNombre(),
                messageResponse.getEstado().getEstado(),
                project.getCompany().getEmail(),
                project.getCoordinator().getGmail()
        );
        rabbitTemplate.convertAndSend(RabbitMQConfig.PROJECT_STATUS_NOTIFICATION_QUEUE, notificationDTO);

        return new ProjectStatusResponse(messageResponse.getEstado().getEstado(), messageResponse.getMessage());
    }

    public String calcularPeriodo(){
        LocalDate today = LocalDate.now();
       return today.getMonthValue() < 6? String.valueOf(today.getYear()) + "-"+1 : String.valueOf(today.getYear()) + "-"+2;
    }

}
