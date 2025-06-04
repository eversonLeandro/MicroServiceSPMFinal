package co.edu.unicauca.studentmicroservice.Controller;


import co.edu.unicauca.studentmicroservice.Entities.Usuario;
import co.edu.unicauca.studentmicroservice.Repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins  = "*")
@RequestMapping( path ="/api/Students/Usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. no se pudo encontrar los Usuarios.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. no se pudo encontrar el Contacto.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody Usuario entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo Guardar el Usuario.\"}");
        }
    }
}
