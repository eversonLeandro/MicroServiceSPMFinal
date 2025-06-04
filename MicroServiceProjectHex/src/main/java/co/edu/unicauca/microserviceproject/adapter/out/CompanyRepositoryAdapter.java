package co.edu.unicauca.microserviceproject.adapter.out;

import co.edu.unicauca.microserviceproject.adapter.out.repository.CompanyJpaRepository;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICompanyRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.infra.entities.CompanyEntity;
import co.edu.unicauca.microserviceproject.infra.mappers.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyRepositoryAdapter implements ICompanyRepositoryPort {

    @Autowired
    private CompanyJpaRepository companyJpaRepository;


    @Override
    public Optional<Company> findCompanyByNit(Long nit) {
        CompanyEntity companyEntity = companyJpaRepository.getByNit(nit);
        return Optional.ofNullable(companyEntity)
                .map(CompanyMapper::entityToDomain);
    }
    @Override
    public Company saveCompany(Company company) {
        CompanyEntity entity = CompanyMapper.domainToEntity(company);
        CompanyEntity companyEntity= companyJpaRepository.save(entity);
        return CompanyMapper.entityToDomain(companyEntity);
    }


}
