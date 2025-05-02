package co.edu.unicauca.microserviceproject.service;
import co.edu.unicauca.microserviceproject.infra.Prototype.ProjectPrototypeRegister;
import co.edu.unicauca.microserviceproject.infra.config.RabbitMQConfig;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectMapperCompany;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectRequest;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectRequestCompany;
import co.edu.unicauca.microserviceproject.entities.Company;
import co.edu.unicauca.microserviceproject.entities.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import co.edu.unicauca.microserviceproject.repository.CompanyRepository;
import co.edu.unicauca.microserviceproject.repository.CoordinatorRepository;
import co.edu.unicauca.microserviceproject.repository.PostulationRepository;
import co.edu.unicauca.microserviceproject.repository.ProjectRepository;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private EntityManager entityManager;

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
    public Project createProject(ProjectRequest dto) throws Exception {
        // Validación exhaustiva
        if (dto == null || dto.getNitCompany() == null) {
            throw new IllegalArgumentException("Datos requeridos faltantes");
        }

        // Carga explícita con verificación de estado
        Company company = companyRepository.findById(dto.getNitCompany())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Compañía con NIT %d no encontrada", dto.getNitCompany())));

        // Verificación adicional del estado de la entidad
        if (!entityManager.contains(company)) {
            company = entityManager.merge(company);
        }

        // Construcción segura del proyecto
        Project project = (Project)projectPrototypeRegister.getGestor().clonar("DEFECTO");
        project.setNombre(dto.getNombre());
        project.setResumen(dto.getResumen());
        project.setDescripcion(dto.getDescripcion());
        project.setObjetivo(dto.getObjetivo());
        project.setTiempoMaximo(dto.getTiempoMaximo());
        project.setPresupuesto(dto.getPresupuesto());
        project.setFechaEntregadaEsperada(dto.getFechaEntregadaEsperada());
        project.setCompany(company); // Asignación crítica

        // Persistencia con verificación
        try {
            Project savedProject = projectRepository.save(project);
            entityManager.flush(); // Fuerza la escritura inmediata

            // Verificación post-guardado
            if (savedProject.getCompany() == null) {
                throw new IllegalStateException("Relación con compañía perdida después de persistir");
            }

            return savedProject;
        } catch (DataIntegrityViolationException e) {
            throw new PersistenceException("Error de integridad referencial", e);
        }
    }

    public void deleteById(Long id) throws Exception {
        projectRepository.deleteById(id);
    }


}
