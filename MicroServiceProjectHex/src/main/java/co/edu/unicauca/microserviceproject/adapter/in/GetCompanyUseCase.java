package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.in.ICompanyFindByNit;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICompanyRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.infra.entities.CompanyEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GetCompanyUseCase implements ICompanyFindByNit {

    final ICompanyRepositoryPort companyRepositoryPort;

    @Override
    public Company getCompanyByNit(Long nit) {
        return companyRepositoryPort.findCompanyByNit(nit)
                .orElseThrow(() -> new EntityNotFoundException("La empresa con NIT " + nit + " no fue encontrada."));
    }
}
