package co.edu.unicauca.studentmicroservice.Service;

import co.edu.unicauca.studentmicroservice.Entities.Student;
import co.edu.unicauca.studentmicroservice.Repositories.StudentRepository;
import co.edu.unicauca.studentmicroservice.infra.config.RabbitMQConfig;
import co.edu.unicauca.studentmicroservice.infra.dto.UsuarioRequest;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.World;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements BaseService<Student> {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    ModelMapper modelMapper= new ModelMapper();
    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        // Mapear a DTO
        TypeMap<Student, UsuarioRequest> propertyMapper = modelMapper.createTypeMap(Student.class, UsuarioRequest.class);
        propertyMapper.addMapping(Student::getCedula, UsuarioRequest::setId);
        propertyMapper.addMapping(Student::getEmail, UsuarioRequest::setEmail);
        propertyMapper.addMapping(Student::getNombre, UsuarioRequest::setUsername);
        propertyMapper.addMapping(Student::getCedula, UsuarioRequest::setContrasenia);
    }

    @Override
    @Transactional
    public List<Student> findAll() throws Exception {
        try{
            List<Student> entities = studentRepository.findAll();
            return entities;
}catch (Exception e){
        throw new Exception(e.getMessage());
        }
        }

    @Override
    @Transactional
    public Student findById(String ide) throws Exception {
        try{
            Optional<Student> entityOptional = studentRepository.findById(Long.valueOf(ide));
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean save(Student entity) throws Exception {
        try{
             studentRepository.save(entity);
            UsuarioRequest usuariDTO = modelMapper.map(entity,UsuarioRequest.class);
            usuariDTO.setRol("STUDENT");
            if (rabbitTemplate.getConnectionFactory() == null) {
                throw new IllegalStateException("RabbitMQ connection factory not initialized");
            }
            // Enviar mensaje
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_STUDENT_CREATED,usuariDTO);
            return true;
        }catch (Exception e){
          // e.printStackTrace();
            return false;
        }
    }
    @Override
    @Transactional
    public boolean saveAll(List<Student> students) throws Exception {
        try {
            List<Student> savedStudents = studentRepository.saveAll(students);
            return savedStudents.size() == students.size();
        } catch (Exception e) {
            // Opcional: puedes loggear el error aquí
            // logger.error("Error al guardar lista de estudiantes: " + e.getMessage());
            return false;
        }
    }
    @Override
    @Transactional
    public boolean update(Long id, Student entity) throws Exception {
        return false;
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        return false;
    }
    @Transactional
    public Student findByUsername(String username) throws Exception {
        try {
            return studentRepository.findByNombre(username); // Asegúrate que este método existe
        } catch (Exception e) {
            throw new Exception("Error al buscar estudiante por username", e);
        }
    }
 @Transactional
    public Student saveTest(Student entity) throws Exception {
        try {
            Student saved = studentRepository.save(entity);
            UsuarioRequest usuariDTO = modelMapper.map(entity,UsuarioRequest.class);
            usuariDTO.setRol("STUDENT");
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_STUDENT_CREATED,usuariDTO);
            return saved;
        } catch (Exception e) {
            throw new Exception("Error al guardar estudiante");
        }
    }
}
