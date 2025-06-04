package co.edu.unicauca.microserviceproject.aplication.port.out;

import co.edu.unicauca.microserviceproject.domain.model.company.Company;

import java.util.Optional;

public interface ICompanyRepositoryPort {
    Optional<Company> findCompanyByNit(Long nit);
    Company saveCompany(Company company);
}
