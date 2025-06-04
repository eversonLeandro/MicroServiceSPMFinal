package co.edu.unicauca.microserviceproject.adapter.out.repository;

import co.edu.unicauca.microserviceproject.infra.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, Long> {

    CompanyEntity getByNit(Long nit);

}
