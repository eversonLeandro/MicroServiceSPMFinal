package co.edu.unicauca.studentmicroservice.Repositories;


import co.edu.unicauca.studentmicroservice.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
