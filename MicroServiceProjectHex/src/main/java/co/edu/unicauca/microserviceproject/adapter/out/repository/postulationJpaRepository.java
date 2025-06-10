package co.edu.unicauca.microserviceproject.adapter.out.repository;

import co.edu.unicauca.microserviceproject.infra.entities.PostulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface postulationJpaRepository extends JpaRepository<PostulationEntity, Long> {
}
