package co.edu.unicauca.microservicepostulation.Controller;
import co.edu.unicauca.microservicepostulation.entity.Postulation;
import co.edu.unicauca.microservicepostulation.service.PostulationService;
import co.edu.unicauca.microservicepostulation.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/postulaciones")
@RequiredArgsConstructor
@RestController
public class PostulationController {
    @Autowired
    private PostulationService postulationService;

    @PostMapping
    public Postulation createPostulation(@RequestParam Long idEstudiante, @RequestParam Long idProyecto) {
        return postulationService.savePostulation(idEstudiante, idProyecto);
    }
    }
