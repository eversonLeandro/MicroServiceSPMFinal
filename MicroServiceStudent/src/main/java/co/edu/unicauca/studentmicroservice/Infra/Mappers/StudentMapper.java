package co.edu.unicauca.studentmicroservice.Infra.Mappers;

import co.edu.unicauca.studentmicroservice.Infra.DTO.StudentDTO;
import co.edu.unicauca.studentmicroservice.Entities.Student;
import co.edu.unicauca.studentmicroservice.Entities.Usuario;
import co.edu.unicauca.studentmicroservice.Repositories.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentMapper {

    private final UsuarioRepository usuarioRepository;

    public StudentMapper(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Student toEntity(StudentDTO dto,Usuario usuario) throws Exception {
        Student student = new Student();
        student.setCodEst(dto.getCodEst());
        student.setCedula(dto.getCedula());
        student.setTelefono(dto.getTelefono());
        student.setEstado(Student.Estado.HABILITADO);
        student.setUsuario(usuario);

        return student;
    }

    public StudentDTO convertToDTO(Student student) {
        return new StudentDTO(
                student.getCodEst(),
                student.getCedula(),
                student.getTelefono(),
                student.getUsuario().getUserName(),
                "null"
        );
    }
}
