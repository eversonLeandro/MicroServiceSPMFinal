package co.edu.unicauca.companymicroservice.Service;

import co.edu.unicauca.companymicroservice.Entities.Company;
import co.edu.unicauca.companymicroservice.Entities.Contacto;
import co.edu.unicauca.companymicroservice.Entities.Project;
import co.edu.unicauca.companymicroservice.Infra.DTO.CompanyDTO;
import co.edu.unicauca.companymicroservice.Infra.DTO.ProjectRequestCompany;
import co.edu.unicauca.companymicroservice.Infra.DTO.UsuarioRequest;
import co.edu.unicauca.companymicroservice.Infra.Mappers.CompanyMapper;
import co.edu.unicauca.companymicroservice.Infra.Mappers.ProjectMapper;
import co.edu.unicauca.companymicroservice.Infra.Mappers.UsuarioMapper;
import co.edu.unicauca.companymicroservice.Repositories.CompanyRepository;
import co.edu.unicauca.companymicroservice.Repositories.ContactoRepository;
import co.edu.unicauca.companymicroservice.Infra.Config.RabbitMQConfig;
import co.edu.unicauca.companymicroservice.Repositories.ProjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService{

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private ContactoRepository contactoRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;
    private ProjectMapper projectMapper = new ProjectMapper();


    public CompanyService(CompanyRepository repository) {
        this.companyRepository = repository;
    }


    @Transactional
    public List<Company> findAll() throws Exception {
        try{
            List<Company> entities = companyRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Company findById(String ID) throws Exception {
        try{
            Optional<Company> entityOptional = companyRepository.findById(ID);
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Transactional
    public boolean save(CompanyDTO entity) throws Exception {
        try{
            Contacto contacto = new Contacto();
            contacto.setTelefono(entity.getTelefono());
            contacto.setNombre(entity.getNombrecontaccto());
            contacto.setApellido(entity.getApellido());
            contacto.setCargo(entity.getCargo());

            Company company = companyMapper.toEntity(entity,contacto);
            companyRepository.save(company);

            contacto.setCompany(company);
            contactoRepository.save(contacto);

            UsuarioRequest userdto=usuarioMapper.obteneruser(entity);
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_COMPANY_CREATED,userdto);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(String idCompany, Company newCompanyData, Project project) throws Exception {
        try {
            Optional<Company> optionalCompany = companyRepository.findById(idCompany);
            if (optionalCompany.isPresent()) {
                Company existingCompany = optionalCompany.get();

                existingCompany.setNombre(newCompanyData.getNombre());
                existingCompany.setEstado(newCompanyData.getEstado());
                existingCompany.setSector(newCompanyData.getSector());

                if (newCompanyData.getContactos() != null && !newCompanyData.getContactos().isEmpty()) {
                    existingCompany.setContactos(newCompanyData.getContactos());
                }

                if (newCompanyData.getProyectos() != null && !newCompanyData.getProyectos().isEmpty()) {
                    existingCompany.setProyectos(newCompanyData.getProyectos());
                }
                List<Project> proyectos = existingCompany.getProyectos();
                proyectos.add(project);
                existingCompany.setProyectos(proyectos);
                projectRepository.save(project);
                companyRepository.save(existingCompany);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar la compañía: " + e.getMessage());
        }
    }

    @Transactional
    public void addProjectToCompany(ProjectRequestCompany projectdto) {
        Optional<Company> companyOptional = companyRepository.findById(String.valueOf(projectdto.getNitCompany()));
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            Project project = projectMapper.projectToEntity(projectdto, company);
            project.setCompany(company);
            company.getProyectos().add(project);
            companyRepository.save(company);
        } else {
            throw new RuntimeException("No se encontró la compañía con NIT: " + projectdto.getNitCompany());
        }
    }


}


