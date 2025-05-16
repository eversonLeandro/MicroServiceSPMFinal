/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.domain.services;

import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.infra.Messages;
import co.edu.unicauca.interfaces.IUserService;
import co.edu.unicauca.main.Main;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Brayan
 */
public class UserServiceProxy implements IUserService {

    private static UserServiceProxy instance;
    private final IUserService realService;
    private static final int MAX_ATTEMPTS = 3;
    private int fallos = 0;

    public UserServiceProxy(IUserService realService) {
        this.realService = realService;
    }

    @Override
    public User authenticate(String username, String password) {

        // 1) Si ya alcanzó el límite, bloqueo
        if (fallos >= MAX_ATTEMPTS) {
            Messages.showMessageDialog(
                    "Usuario '" + username + "' bloqueado tras " + MAX_ATTEMPTS + " intentos.",
                    "Login bloqueado"
            );
            Main.cerrarGUI();
            return null;
        }

        fallos++;
        User user = realService.authenticate(username, password);
        if (user != null) {
            fallos = 0;
        } else {
            Messages.showMessageDialog(
                    "Usuario no encontrado. Intentos restantes: '" + getRemainingAttempts(), "Login bloqueado");
        }
        return user;
    }

    public static UserServiceProxy getInstance(IUserService realService) {
        if (instance == null) {
            instance = new UserServiceProxy(realService);
        }
        return instance;
    }

    public int getRemainingAttempts() {
        return MAX_ATTEMPTS - fallos;
    }
}
