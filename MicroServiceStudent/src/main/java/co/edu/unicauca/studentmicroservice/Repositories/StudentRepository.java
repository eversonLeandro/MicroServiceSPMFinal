package co.edu.unicauca.studentmicroservice.Repositories;

import co.edu.unicauca.studentmicroservice.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
