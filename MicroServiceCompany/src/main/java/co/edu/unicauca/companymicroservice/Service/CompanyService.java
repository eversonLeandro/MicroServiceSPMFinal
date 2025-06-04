package co.edu.unicauca.companymicroservice.Service;

import co.edu.unicauca.companymicroservice.Entities.Company;
import co.edu.unicauca.companymicroservice.Entities.Contacto;
import co.edu.unicauca.companymicroservice.Infra.DTO.CompanyDTO;
import co.edu.unicauca.companymicroservice.Infra.DTO.CompanyRequestProject;
import co.edu.unicauca.companymicroservice.Infra.DTO.ProjectRequestCompany;
import co.edu.unicauca.companymicroservice.Infra.DTO.UsuarioRequest;
import co.edu.unicauca.companymicroservice.Infra.Mappers.CompanyMapper;
import co.edu.unicauca.companymicroservice.Infra.Mappers.UsuarioMapper;
import co.edu.unicauca.companymicroservice.Repositories.CompanyRepository;
import co.edu.unicauca.companymicroservice.Repositories.ContactoRepository;
import co.edu.unicauca.companymicroservice.Infra.Config.RabbitMQConfig;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private UsuarioMapper usuarioMapper;

    public CompanyService(CompanyRepository repository) {
        this.companyRepository = repository;
    }


    public List<CompanyDTO> findAllDTOs() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDTO> dtos = new ArrayList<>();
        for (Company company : companies) {
            dtos.add(companyMapper.toDto(company));
        }
        return dtos;
    }

    @Transactional
    public CompanyDTO  findByNit(String nit) throws Exception {
            Optional<Company> company = companyRepository.findById(nit);
        if (company.isEmpty()) {
            return null;
        }
        return companyMapper.toDto(company.orElse(null));
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

            UsuarioRequest userdto=usuarioMapper.obteneruser(entity);
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_COMPANY_CREATED, userdto);

            CompanyRequestProject companyRequestProject = new CompanyRequestProject();
            companyRequestProject.setNit(Long.valueOf(company.getNit()));
            companyRequestProject.setNombre(company.getNombre());
            companyRequestProject.setEmail(company.getEmail());

            rabbitTemplate.convertAndSend(RabbitMQConfig.COMPANY_QUEUE,companyRequestProject);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean update(String idCompany, Company newCompanyData) throws Exception {
        try {
            Optional<Company> optionalCompany = companyRepository.findById(idCompany);
            if (optionalCompany.isPresent()) {
                Company existingCompany = optionalCompany.get();

                existingCompany.setNombre(newCompanyData.getNombre());
                existingCompany.setEstado(newCompanyData.getEstado());
                existingCompany.setSector(newCompanyData.getSector());

                if (newCompanyData.getContacto() != null ) {
                    existingCompany.setContacto(newCompanyData.getContacto());
                }

                if (newCompanyData.getProyectos() != null && !newCompanyData.getProyectos().isEmpty()) {
                    existingCompany.setProyectos(newCompanyData.getProyectos());
                }

                companyRepository.save(existingCompany);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar la compañía: " + e.getMessage());
        }
    }

}


