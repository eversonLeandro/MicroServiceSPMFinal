package co.edu.unicauca.microservicepostulation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unicauca.microservicepostulation.entity.Postulation;
public interface PostulationRepository extends JpaRepository<Postulation, Long>{
    boolean existsByIdEstudianteAndIdProyecto(Long idEstudiante, Long idProyecto);
}
