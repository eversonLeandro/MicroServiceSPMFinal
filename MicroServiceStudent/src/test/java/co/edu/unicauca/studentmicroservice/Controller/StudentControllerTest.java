package co.edu.unicauca.studentmicroservice.Controller;

import co.edu.unicauca.studentmicroservice.Entities.Student;
import co.edu.unicauca.studentmicroservice.Service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents_Success() throws Exception {
        Student student1 = new Student("123", "20201", "test1@email.com", "Student 1", "123456");
        Student student2 = new Student("456", "20202", "test2@email.com", "Student 2", "654321");
        List<Student> students = Arrays.asList(student1, student2);
        
        when(studentService.findAll()).thenReturn(students);

        ResponseEntity<?> response = studentController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
    }

    @Test
    void testGetAllStudents_NotFound() throws Exception {
        when(studentService.findAll()).thenThrow(new RuntimeException("Error"));
        ResponseEntity<?> response = studentController.getAll();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Error. no se pudo encontrar los Estudiantes."));
    }

    @Test
    void testGetStudentById_Success() throws Exception {
        String studentId = "20201";
        Student student = new Student("123", studentId, "test@email.com", "Test Student", "123456");
        
        when(studentService.findById(studentId)).thenReturn(student);

        ResponseEntity<?> response = studentController.getOne(studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student, response.getBody());
    }

    @Test
    void testGetStudentById_NotFound() throws Exception {
        String studentId = "999";
        when(studentService.findById(studentId)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<?> response = studentController.getOne(studentId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Error. no se pudo encontrar el Estudiante."));
    }

    @Test
    void testSaveStudent_Success() throws Exception {
        Student student = new Student("123", "20201", "test@email.com", "Test Student", "123456");
        
        when(studentService.save(student)).thenReturn(true);

        ResponseEntity<?> response = studentController.save(student);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testSaveStudent_BadRequest() throws Exception {
        Student student = new Student();
        when(studentService.saveTest(student)).thenReturn(student);

        ResponseEntity<?> response = studentController.saveTest(student);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Error. no se pudo Guardar el Estudiante."));
    }
}