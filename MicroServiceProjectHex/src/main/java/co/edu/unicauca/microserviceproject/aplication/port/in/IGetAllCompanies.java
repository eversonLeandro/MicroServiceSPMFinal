package co.edu.unicauca.microserviceproject.aplication.port.in;

import co.edu.unicauca.microserviceproject.domain.model.company.Company;

import java.util.List;


public interface IGetAllCompanies {
    List<Company> getAllCompanies();
}
