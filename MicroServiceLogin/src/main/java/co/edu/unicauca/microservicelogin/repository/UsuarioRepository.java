package co.edu.unicauca.microservicelogin.repository;
import co.edu.unicauca.microservicelogin.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<User, String>  {
    Optional<User> findByUsernameAndContrasenia(String username, String password);

    Optional<User> findByUsername(String username);
}

