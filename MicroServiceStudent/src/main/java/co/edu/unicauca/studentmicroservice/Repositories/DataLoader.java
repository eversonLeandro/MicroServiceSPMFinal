package co.edu.unicauca.studentmicroservice.Repositories;

import co.edu.unicauca.studentmicroservice.Entities.Student;
import co.edu.unicauca.studentmicroservice.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
@Autowired
    private StudentService studentservice;

    @Override
    public void run(String... args) throws Exception {
        // 1) Comprobar si ya hay datos para no duplicar
        if (studentservice.findAll().isEmpty()) {
            // Ahora creamos los estudiantes
            Student student1 = new Student("1234567890", "202010001", "juan.perez@unicauca.edu.co", "Juan Pérez", "3151234567");
            Student student2 = new Student("0987654321", "202010002", "maria.gomez@unicauca.edu.co", "María Gómez", "3209876543");
            Student student3 = new Student("5678901234", "202010003", "carlos.rodriguez@unicauca.edu.co", "Carlos Rodríguez", "3105678901");
            Student student4 = new Student("4321098765", "202010004", "laura.hernandez@unicauca.edu.co", "Laura Hernández", "3004321098");
            Student student5 = new Student("9876543210", "202010005", "diego.castro@unicauca.edu.co", "Diego Castro", "3189876543");
            Student student6 = new Student("1357924680", "202010006", "ana.martinez@unicauca.edu.co", "Ana Martínez", "3171357924");
            Student student7 = new Student("2468013579", "202010007", "pedro.gonzalez@unicauca.edu.co", "Pedro González", "3142468013");
            Student student8 = new Student("1029384756", "202010008", "sofia.ramirez@unicauca.edu.co", "Sofía Ramírez", "3121029384");
            Student student9 = new Student("6574839201", "202010009", "javier.lopez@unicauca.edu.co", "Javier López", "3196574839");
            Student student10 = new Student("0816", "202010010", "camila@unicauca.edu.co", "Camila", "3153847562");

            // Guardamos todos los estudiantes
            studentservice.saveAll(Arrays.asList(
                    student1, student2, student3, student4, student5,student6,student7,student8,student9,student10
            ));


            // … puedes añadir tantos como necesites
            System.out.println("⚡ Datos iniciales cargados: students quemados.");
        }
    }
}
