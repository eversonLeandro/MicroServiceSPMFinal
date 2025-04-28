/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.infra;

import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.interfaces.IFrameFactory;
import co.edu.unicauca.view.GUIGestionSottwareStudent;
import javax.swing.JFrame;

/**
 *
 * @author Brayan
 */
public class FrameFactory implements IFrameFactory {

    @Override
    public JFrame createFrame(User user) {

        switch (user.getRol()) {
            case "STUDENT":
                return new GUIGestionSottwareStudent(user);
            case "COORDINADOR":
               
            case "EMPRESA":
                

            // Agrega otros roles según necesites
            default:
                return null;
        }

    }

}
