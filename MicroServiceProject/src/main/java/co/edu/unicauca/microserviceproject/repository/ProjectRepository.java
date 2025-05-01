package co.edu.unicauca.microserviceproject.repository;

import co.edu.unicauca.microserviceproject.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByCompany_Nit(Long companyNit);

}
