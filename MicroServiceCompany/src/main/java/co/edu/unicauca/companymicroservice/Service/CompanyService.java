package co.edu.unicauca.companymicroservice.Service;

import co.edu.unicauca.companymicroservice.Entities.Company;
import co.edu.unicauca.companymicroservice.Entities.Contacto;
import co.edu.unicauca.companymicroservice.Infra.DTO.CompanyDTO;
import co.edu.unicauca.companymicroservice.Infra.Mappers.CompanyMapper;
import co.edu.unicauca.companymicroservice.Repositories.CompanyRepository;
import co.edu.unicauca.companymicroservice.Repositories.ContactoRepository;
import jakarta.transaction.Transactional;
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

                if (newCompanyData.getContactos() != null && !newCompanyData.getContactos().isEmpty()) {
                    existingCompany.setContactos(newCompanyData.getContactos());
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


