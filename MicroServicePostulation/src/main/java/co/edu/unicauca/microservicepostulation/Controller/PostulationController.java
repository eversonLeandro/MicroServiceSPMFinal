package co.edu.unicauca.microservicepostulation.Controller;
import co.edu.unicauca.microservicepostulation.entity.Postulation;
import co.edu.unicauca.microservicepostulation.service.PostulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;
import jakarta.validation.Valid;
@RequestMapping("/postulaciones")
@RequiredArgsConstructor
@RestController
public class PostulationController {
    @Autowired
    private PostulationService postulationService;

    @PostMapping
    public Postulation createPostulation(@Valid @RequestBody Postulation postulation) {
        return postulationService.savePostulation(postulation.getIdEstudiante(), postulation.getIdProyecto());
    }
    @GetMapping
    public ResponseEntity<List<Postulation>> getAllPostulations() {
        List<Postulation> postulaciones = postulationService.getAllPostulations();
        return ResponseEntity.ok(postulaciones);
    }

    @GetMapping("/student/{idEstudiante}")
    public ResponseEntity<List<Postulation>> getPostulationsByStudent(@PathVariable Long idEstudiante) {
        List<Postulation> postulaciones = postulationService.getPostulationsByStudent(idEstudiante);
        return ResponseEntity.ok(postulaciones);
    }
    }
