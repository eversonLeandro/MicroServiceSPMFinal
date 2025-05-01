package co.edu.unicauca.studentmicroservice.Controller;

import co.edu.unicauca.studentmicroservice.Infra.DTO.StudentDTO;
import co.edu.unicauca.studentmicroservice.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins  = "*")
@RequestMapping("StudentMicroservice/Students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. no se pudo encontrar los Estudiantes.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. no se pudo encontrar el Estudiante.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid@RequestBody StudentDTO entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo Guardar el Estudiante.\"}");
        }
    }
}
