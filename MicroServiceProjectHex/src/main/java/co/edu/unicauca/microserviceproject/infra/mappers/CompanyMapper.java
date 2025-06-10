package co.edu.unicauca.microserviceproject.infra.mappers;

import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.company.VO.Email;
import co.edu.unicauca.microserviceproject.infra.entities.CompanyEntity;

public class CompanyMapper {

    public static CompanyEntity domainToEntity(Company entity) {
        if (entity == null) return null;
         CompanyEntity company = CompanyEntity.builder()
                .nit(entity.getNit())
                .nombre(entity.getNombre())
                .email(entity.getEmail().toString())
                .build();
        return company;
    }

    public static Company entityToDomain(CompanyEntity companyEntity) {
        if (companyEntity == null) return null;
        return new Company(companyEntity.getNit(),
                companyEntity.getNombre(),
                new Email(companyEntity.getEmail())
        );
    }



}
