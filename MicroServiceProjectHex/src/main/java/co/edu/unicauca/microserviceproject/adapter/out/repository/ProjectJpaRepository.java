package co.edu.unicauca.microserviceproject.adapter.out.repository;

import co.edu.unicauca.microserviceproject.infra.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, Long> {
    ProjectEntity findProjectByProjectId(Long  ide);
    List<ProjectEntity> getAllByCompany_Nit(Long nit);
}
