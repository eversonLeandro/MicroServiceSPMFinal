package co.edu.unicauca.companymicroservice.Infra.Mappers;

import co.edu.unicauca.companymicroservice.Entities.Company;
import co.edu.unicauca.companymicroservice.Entities.Contacto;
import co.edu.unicauca.companymicroservice.Infra.DTO.CompanyDTO;
import co.edu.unicauca.companymicroservice.Repositories.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyMapper {

    private final ContactoRepository contactoRepository;

    @Autowired
    public CompanyMapper(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    public CompanyDTO toDto(Company company) {
        if (company == null) return null;

        Contacto contacto = company.getContacto();

        return new CompanyDTO(
                company.getNit(),
                company.getNombre(),
                contacto != null ? contacto.getEmail() : null,
                contacto != null ? contacto.getTelefono() : null,
                contacto != null ? contacto.getNombre() : null,
                contacto != null ? contacto.getApellido() : null,
                company.getSector().toString(),
                contacto != null ? contacto.getCargo() : null,
                company.getEstado().toString()
        );
    }
    public Company toEntity(CompanyDTO dto , Contacto contacto) {

        Company company = new Company();
        company.setNit(dto.getNit());
        company.setNombre(dto.getNombre());
        company.setSector(Company.Sector.valueOf(dto.getSector().toUpperCase()));
        company.setEstado(Company.Estado.valueOf(dto.getEstado()));
        company.setContacto(new Contacto(dto.getApellido(),dto.getCargo(),dto.getEmail(),dto.getNombre(),dto.getTelefono()));
        company.setEstado(Company.Estado.HABILITADO);

        return company;
    }
}
