package co.edu.unicauca.main;


import co.edu.unicauca.access.Factory;
import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.domain.services.UserService;
import co.edu.unicauca.infra.FrameFactory;
import co.edu.unicauca.interfaces.IFrameFactory;
import co.edu.unicauca.interfaces.IRepository;
import co.edu.unicauca.view.GUIGestionSottwareStudent;
import co.edu.unicauca.view.GUILogin;
import javax.swing.JFrame;

public class Main {

    private static GUILogin loginInstance = null;
    private static GUIGestionSottwareStudent studentInstance =null;

    public static void mostrarLogin() {
        /*User user = new User(105L, "user123", "pass123", "user@example.com", "STUDENT", "ACTIVO");
        if (studentInstance == null) {  // Solo se crea una vez
            IFrameFactory frameFactory = new FrameFactory();
            studentInstance = new GUIGestionSottwareStudent(user);
           // studentInstance.setSize(450, 380);
            studentInstance.setLocationRelativeTo(null);
        }
        studentInstance.setVisible(true);
        studentInstance.setExtendedState(JFrame.NORMAL);
        */
            if (loginInstance == null) {  // Solo se crea una vez
            IRepository userRepository = Factory.getInstance().getRepository("usuario");
            UserService service = new UserService(userRepository);
            IFrameFactory frameFactory = new FrameFactory();
            loginInstance = new GUILogin(service, frameFactory);
            loginInstance.setSize(450, 380);
            loginInstance.setLocationRelativeTo(null);
        }
        loginInstance.setVisible(true);
        loginInstance.setExtendedState(JFrame.NORMAL);

    }

    public static void main(String[] args) {
        mostrarLogin();
    }
}
