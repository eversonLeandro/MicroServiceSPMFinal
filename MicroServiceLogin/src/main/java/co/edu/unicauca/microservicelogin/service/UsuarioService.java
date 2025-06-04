package co.edu.unicauca.microservicelogin.service;

import co.edu.unicauca.microservicelogin.entities.User;
import co.edu.unicauca.microservicelogin.infra.dto.UsuarioRequest;
import co.edu.unicauca.microservicelogin.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.core.Response;


@Service
public class UsuarioService {
    @Autowired
    private KeycloakUserService keycloakUserService;

    ModelMapper modelMapper= new ModelMapper();
    @Autowired
    private UsuarioRepository repository;

    public Optional<User> findByUsernameAndContrasenia(String username, String password) {
        return repository.findByUsernameAndContrasenia(username,password);
    }
    @Transactional
    public Optional<User> findByUsername(String username) {
        // Como en tu entidad username es @Id, puedes usar findById:
        return repository.findByUsername(username);
    }
    @Transactional
    public User save(UsuarioRequest usuario) {
        try {
            // 1. Registrar en Keycloak
            keycloakUserService.crearUsuarioEnKeycloak(
                    usuario.getUsername(),
                    usuario.getContrasenia(),
                    usuario.getEmail(),
                    usuario.getRol().toLowerCase()
            );
        } catch (ClientErrorException e) {
            if (e.getResponse().getStatus() == 409) {
                System.out.println("Usuario ya existe en Keycloak: " + usuario.getUsername());

            } else {
                throw e;
            }
        }
        User user = modelMapper.map(usuario, User.class);
        return repository.save(user);
    }
    @Transactional
    public User updateStudent(UsuarioRequest usuariomodificado) throws Exception {

        Optional<User> instance = repository.findByUsernameAndContrasenia(usuariomodificado.getUsername(),usuariomodificado.getContrasenia());
        if (instance.isEmpty()) {
            throw new IllegalAccessException("El usuario: " + usuariomodificado.getUsername()+ " no existe");
        }
        User existingusuario = instance.get();
        
        if (usuariomodificado.getContrasenia()!= null) {
            existingusuario.setContrasenia(usuariomodificado.getContrasenia());
        }
        if (usuariomodificado.getEmail()!= null) {
            existingusuario.setEmail(usuariomodificado.getEmail());
        }
        return repository.save(existingusuario);
    }
    public List<User> findAll() throws Exception {
            return repository.findAll();

    }
}
