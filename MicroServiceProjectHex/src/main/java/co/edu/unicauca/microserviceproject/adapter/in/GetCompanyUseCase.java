package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.in.ICompanyFindByNit;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICompanyRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
