package co.edu.unicauca.studentmicroservice.Controller;

import co.edu.unicauca.studentmicroservice.Infra.DTO.TeamDTO;
import co.edu.unicauca.studentmicroservice.Service.TeamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins  = "*")
@RequestMapping("/api/Students/Teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(teamService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. no se pudo encontrar los Equipos.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(teamService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. no se pudo encontrar el Equipo.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody TeamDTO entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(teamService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo Guardar el Equipo.\"}");
        }
    }
}
