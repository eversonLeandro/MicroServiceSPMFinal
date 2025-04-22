package co.edu.unicauca.microserviceproject.repository;

import co.edu.unicauca.microserviceproject.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
