package co.edu.unicauca.studentmicroservice.Repositories;

import co.edu.unicauca.studentmicroservice.Entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
}
