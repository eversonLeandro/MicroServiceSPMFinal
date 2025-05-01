package co.edu.unicauca.companymicroservice.Repositories;

import co.edu.unicauca.companymicroservice.Entities.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
}
