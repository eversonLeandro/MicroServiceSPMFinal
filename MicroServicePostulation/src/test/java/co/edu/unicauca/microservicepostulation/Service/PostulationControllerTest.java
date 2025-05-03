package co.edu.unicauca.microservicepostulation.Service;

import co.edu.unicauca.microservicepostulation.Controller.PostulationController;
import co.edu.unicauca.microservicepostulation.entity.Postulation;
import co.edu.unicauca.microservicepostulation.service.PostulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostulationControllerTest {

    @Mock
    private PostulationService postulationService;

    @InjectMocks
    private PostulationController postulationController;

    private Postulation postulation;

    @BeforeEach
    void setUp() {
        postulation = new Postulation();
        postulation.setId(1L);
        postulation.setIdEstudiante(100L);
        postulation.setIdProyecto(200L);
        postulation.setFechaPostulacion(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    void createPostulation_Success() {
        when(postulationService.savePostulation(any(Long.class), any(Long.class)))
            .thenReturn(postulation);

        Postulation result = postulationController.createPostulation(postulation);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertNotNull(result.getFechaPostulacion());
        verify(postulationService, times(1)).savePostulation(100L, 200L);
    }

    @Test
    void getAllPostulations_Success() {
        List<Postulation> postulations = Arrays.asList(postulation);
        when(postulationService.getAllPostulations()).thenReturn(postulations);

        ResponseEntity<List<Postulation>> response = postulationController.getAllPostulations();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertNotNull(response.getBody().get(0).getFechaPostulacion());
    }

    @Test
    void getPostulationsByStudent_Success() {
        List<Postulation> postulations = Arrays.asList(postulation);
        when(postulationService.getPostulationsByStudent(100L)).thenReturn(postulations);

        ResponseEntity<List<Postulation>> response = postulationController.getPostulationsByStudent(100L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(200L, response.getBody().get(0).getIdProyecto());
        assertNotNull(response.getBody().get(0).getFechaPostulacion());
    }
}