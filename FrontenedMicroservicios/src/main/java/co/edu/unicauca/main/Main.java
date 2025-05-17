package co.edu.unicauca.main;

import co.edu.unicauca.access.Factory;
import co.edu.unicauca.access.ProjectRepository;
import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.domain.services.UserService;
import co.edu.unicauca.domain.services.UserServiceProxy;
import co.edu.unicauca.infra.FrameFactory;
import co.edu.unicauca.interfaces.IFrameFactory;
import co.edu.unicauca.interfaces.IRepository;
import co.edu.unicauca.interfaces.IUserService;
import co.edu.unicauca.view.GUIGestionSofwareCoordination;

import co.edu.unicauca.view.GUIGestionSottwareStudent;
import co.edu.unicauca.view.GUILogin;
import javax.swing.JFrame;

public class Main {

    private static GUILogin loginInstance = null;
    private static GUIGestionSottwareStudent studentInstance = null;

    public static void mostrarLogin() {

        if (loginInstance == null) {  // Solo se crea una vez
            IRepository userRepository = Factory.getInstance().getRepository("usuario");
            IUserService realservice = new UserService(userRepository);

            IUserService serviceproxy = UserServiceProxy.getInstance(realservice);
            IFrameFactory frameFactory = new FrameFactory();

            loginInstance = new GUILogin(serviceproxy, frameFactory);
            loginInstance.setSize(450, 380);
            loginInstance.setLocationRelativeTo(null);
            loginInstance.setVisible(true);
            loginInstance.setExtendedState(JFrame.NORMAL);
        } else {
            loginInstance.setSize(450, 380);
            loginInstance.setLocationRelativeTo(null);
            loginInstance.setVisible(true);
            loginInstance.setExtendedState(JFrame.NORMAL);
        }
    }

    public static void main(String[] args) {
        mostrarLogin();
    }

    public static void cerrarGUI() {
        if (loginInstance != null) {
            loginInstance.dispose();
            loginInstance = null;
        }
    }
}
