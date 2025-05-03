 package co.edu.unicauca.domain.services;

import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.interfaces.IRepository;

/**
 *
 
@author Brayan*/
public class UserService {

    private IRepository userRepository;

    public UserService(IRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username) {
        User user = (User)userRepository.found(username);
        return user;
    }

}