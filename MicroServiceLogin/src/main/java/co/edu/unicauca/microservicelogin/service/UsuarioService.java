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

@Service
public class UsuarioService {
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
        User user=modelMapper.map(usuario, User.class);
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
