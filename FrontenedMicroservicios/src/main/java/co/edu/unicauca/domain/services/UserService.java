package co.edu.unicauca.domain.services;

import co.edu.unicauca.access.SessionManager;
import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.infra.Messages;
import co.edu.unicauca.interfaces.IRepository;
import co.edu.unicauca.interfaces.IUserService;

/**
 *
 *
 * @author Brayan
 */
public class UserService implements IUserService {

    private IRepository userRepository;

    public UserService(IRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authenticate(String username, String password) {
        // Paso 1: pedir token a Keycloak
    String token = AuthService.getTokenFromKeycloak(username, password);
    System.out.println("Token received: " + token);
    
    if (token == null) {
        Messages.showMessageDialog("Credenciales invalidas.", "Error");
        return null;
    }

    // Paso 2: guardar token para futuros requests
    SessionManager.setToken(token);
    System.out.println("Token stored in session");

    // Paso 3: obtener usuario desde tu microservicio de usuarios
    User u = (User) userRepository.found(username);
    if (u == null) {
        System.err.println("Usuario no encontrado en el sistema después de autenticación exitosa");
    }
    return u;
    }
}

