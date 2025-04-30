package co.edu.unicauca.main;


import co.edu.unicauca.infra.FrameFactory;
import co.edu.unicauca.interfaces.IFrameFactory;
import co.edu.unicauca.view.GUILogin;
import javax.swing.JFrame;

public class Main {

    private static GUILogin loginInstance = null;

    public static void mostrarLogin() {
        if (loginInstance == null) {  // Solo se crea una vez
            IFrameFactory frameFactory = new FrameFactory();
            loginInstance = new GUILogin(frameFactory);
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
