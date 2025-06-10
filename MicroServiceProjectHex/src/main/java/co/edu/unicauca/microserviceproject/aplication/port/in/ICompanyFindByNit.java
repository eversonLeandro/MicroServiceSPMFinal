package co.edu.unicauca.microserviceproject.aplication.port.in;

import co.edu.unicauca.microserviceproject.domain.model.company.Company;

public interface ICompanyFindByNit {
    Company getCompanyByNit(Long nit);
}
