package co.edu.unicauca.microservicepostulation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unicauca.microservicepostulation.entity.Postulation;

import java.util.List;

public interface PostulationRepository extends JpaRepository<Postulation, Long>{
    boolean existsByIdEstudianteAndIdProyecto(Long idEstudiante, Long idProyecto);
    List<Postulation> findByIdEstudiante(Long idEstudiante);
}
