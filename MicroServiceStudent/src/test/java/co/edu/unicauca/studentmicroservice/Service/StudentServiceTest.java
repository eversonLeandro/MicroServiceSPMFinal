package co.edu.unicauca.studentmicroservice.Service;

import co.edu.unicauca.studentmicroservice.Entities.Student;
import co.edu.unicauca.studentmicroservice.Infra.Config.TestRabbitMQConfig;
import co.edu.unicauca.studentmicroservice.Repositories.StudentRepository;
import co.edu.unicauca.studentmicroservice.infra.config.RabbitMQConfig;
import co.edu.unicauca.studentmicroservice.infra.dto.UsuarioRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Import(TestRabbitMQConfig.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private StudentService studentService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll_Success() throws Exception {
        Student student1 = new Student("123", "20201", "test1@email.com", "Student 1", "123456");
        Student student2 = new Student("456", "20202", "test2@email.com", "Student 2", "654321");
        List<Student> students = Arrays.asList(student1, student2);
        
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.findAll();

        assertEquals(2, result.size());
        assertEquals(students, result);
    }

    @Test
    void testFindAll_Exception() {
        when(studentRepository.findAll()).thenThrow(new RuntimeException("Database error"));
        assertThrows(Exception.class, () -> studentService.findAll());
    }

    @Test
    void testFindById_Success() throws Exception {
        String studentId = "20201";
        Student student = new Student("123", studentId, "test@email.com", "Test Student", "123456");
        
        when(studentRepository.findById(Long.valueOf(studentId))).thenReturn(Optional.of(student));
        Student result = studentService.findById(studentId);
        assertEquals(student, result);
    }

    @Test
    void testFindById_Exception() {
        String studentId = "999";
        when(studentRepository.findById(Long.valueOf(studentId))).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> studentService.findById(studentId));
    }

    @Test
    void testSave_Success() throws Exception {
        Student student = new Student("123", "20201", "test@email.com", "Test Student", "123456");
        Student savedStudent = new Student("123", "20201", "test@email.com", "Test Student", "123456");
        when(studentRepository.save(student)).thenReturn(savedStudent);
        Student result = studentService.saveTest(student);

        assertNotNull(result);
        assertEquals(savedStudent, result);
    }

    @Test
    void testSave_Failure() throws Exception {
        Student student = new Student();
        when(studentRepository.save(student)).thenThrow(new RuntimeException("Error"));

        boolean result = studentService.save(student);

        assertFalse(result);
    }

    @Test
    void testSaveAll_Success() throws Exception {
        Student student1 = new Student("123", "20201", "test1@email.com", "Student 1", "123456");
        Student student2 = new Student("456", "20202", "test2@email.com", "Student 2", "654321");
        List<Student> students = Arrays.asList(student1, student2);
        
        when(studentRepository.saveAll(students)).thenReturn(students);

        boolean result = studentService.saveAll(students);

        assertTrue(result);
    }

    @Test
    void testSaveAll_Failure() throws Exception {
        List<Student> students = Arrays.asList(new Student());
        when(studentRepository.saveAll(students)).thenThrow(new RuntimeException("Error"));

        boolean result = studentService.saveAll(students);

        assertFalse(result);
    }
}