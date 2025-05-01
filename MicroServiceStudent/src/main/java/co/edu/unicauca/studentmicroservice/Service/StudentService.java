package co.edu.unicauca.studentmicroservice.Service;

import co.edu.unicauca.studentmicroservice.Entities.Usuario;
import co.edu.unicauca.studentmicroservice.Infra.DTO.StudentDTO;
import co.edu.unicauca.studentmicroservice.Entities.Student;
import co.edu.unicauca.studentmicroservice.Infra.Mappers.StudentMapper;
import co.edu.unicauca.studentmicroservice.Repositories.StudentRepository;
import co.edu.unicauca.studentmicroservice.Repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Transactional
    public List<StudentDTO> findAll() throws Exception {
        try{
            List<Student> entities = studentRepository.findAll();
            return entities.stream().map(studentMapper::convertToDTO).collect(Collectors.toList());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public StudentDTO findById(String id) throws Exception {
        try{
            Optional<Student> entityOptional = studentRepository.findById(Long.valueOf(id));
            if (entityOptional.isPresent()) {
                return studentMapper.convertToDTO(entityOptional.get());
            } else {
                throw new Exception("Estudiante no encontrado");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public boolean save(StudentDTO entity) throws Exception {
        try{
            Usuario usuario = new Usuario();
            usuario.setUserName(entity.getUserName());
            usuario.setRol(Usuario.Rol.ESTUDIANTE);
            usuario.setEstado(Usuario.Estado.HABILITADO);
            usuario.setEmail(entity.getEmail());
            usuario.setPass(entity.getCedula());

            Student student = studentMapper.toEntity(entity,usuario);
            usuarioRepository.save(usuario);
            
            studentRepository.save(student);

            return true;
        }catch (Exception e){
            return false;
        }
    }
}
