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
        CompanyDTO dto = new CompanyDTO();
        dto.setNit(company.getNit());
        dto.setNombre(company.getNombre());
        dto.setTelefono(company.getContactos().get(0).getTelefono());
        dto.setNombrecontaccto(company.getContactos().get(0).getNombre());
        dto.setSector(String.valueOf(company.getSector()));
        dto.setEstado(company.getEstado().name());
        return dto;
    }

    public Company toEntity(CompanyDTO dto , Contacto contacto) {
        List<Contacto> contactos = new ArrayList<>();
        Company company = new Company();
        company.setNit(dto.getNit());
        company.setNombre(dto.getNombre());
        company.setSector(Company.Sector.valueOf(dto.getSector().toUpperCase()));
        company.setEstado(Company.Estado.valueOf(dto.getEstado()));
        contactos.add(contacto);
        company.setContactos(contactos);
        company.setEstado(Company.Estado.HABILITADO);

        return company;
    }
}
