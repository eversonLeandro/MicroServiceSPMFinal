/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.interfaces;

import co.edu.unicauca.domain.entities.User;
import javax.swing.JFrame;

/**
 *
 * @author Brayan
 */
public interface IFrameFactory {
    JFrame createFrame(User user);
}
