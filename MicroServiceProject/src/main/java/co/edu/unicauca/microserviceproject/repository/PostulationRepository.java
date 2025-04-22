package co.edu.unicauca.microserviceproject.repository;

import co.edu.unicauca.microserviceproject.entities.Postulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostulationRepository extends JpaRepository<Postulation, Long> {
}
