package co.edu.unicauca.microserviceproject.adapter.out.repository;

import co.edu.unicauca.microserviceproject.infra.entities.CoordinatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CoordinatorJpaRepository extends JpaRepository<CoordinatorEntity,Long> {
    CoordinatorEntity getByCodCor(Long  codCord);
}
