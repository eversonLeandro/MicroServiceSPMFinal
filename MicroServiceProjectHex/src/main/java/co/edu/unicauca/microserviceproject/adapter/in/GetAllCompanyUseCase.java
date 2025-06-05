package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.in.IGetAllCompanies;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICompanyRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class GetAllCompanyUseCase implements IGetAllCompanies {

    private final ICompanyRepositoryPort companyRepositoryPort;


    @Override
    public List<Company> getAllCompanies() {
        return companyRepositoryPort.findAllCompanies();
    }
}
