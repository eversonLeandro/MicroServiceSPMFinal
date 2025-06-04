package co.edu.unicauca.microservicepostulation.Repository;

import co.edu.unicauca.microservicepostulation.entity.Postulation;
import co.edu.unicauca.microservicepostulation.repository.PostulationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PostulationRepositoryTest {

    @Autowired
    private PostulationRepository postulationRepository;

    @Test
    void whenSavePostulation_thenCorrectTimestamp() {
        // Arrange
        Postulation postulation = new Postulation();
        postulation.setIdEstudiante(100L);
        postulation.setIdProyecto(200L);

        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        postulation.setFechaPostulacion(now);

        Postulation saved = postulationRepository.save(postulation);
        Optional<Postulation> found = postulationRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertNotNull(found.get().getFechaPostulacion());
        assertEquals(
                now.truncatedTo(ChronoUnit.MINUTES),
                found.get().getFechaPostulacion().truncatedTo(ChronoUnit.MINUTES)
        );
    }

    @Test
    void whenFindByIdEstudiante_thenReturnPostulationsWithDates() {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        Postulation postulation1 = new Postulation();
        postulation1.setIdEstudiante(100L);
        postulation1.setIdProyecto(200L);
        postulation1.setFechaPostulacion(now);

        Postulation postulation2 = new Postulation();
        postulation2.setIdEstudiante(100L);
        postulation2.setIdProyecto(300L);
        postulation2.setFechaPostulacion(now);

        postulationRepository.save(postulation1);
        postulationRepository.save(postulation2);

        List<Postulation> result = postulationRepository.findByIdEstudiante(100L);

        assertEquals(2, result.size());

        LocalDateTime date1 = result.get(0).getFechaPostulacion().truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime date2 = result.get(1).getFechaPostulacion().truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime expectedDate = now.truncatedTo(ChronoUnit.MINUTES);

        assertEquals(expectedDate, date1);
        assertEquals(expectedDate, date2);
    }
}