package co.edu.unicauca.domain.services;

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
        User u = (User) userRepository.found(username);
        if (u != null) {
            if (u.getContrasenia().equals(password)) {
                return u;
            } else {
                Messages.showMessageDialog("Credenciales invalidad", "Atención");
            }
        }

        return null;
    }

}
