/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.infra;

/**
 *
 * @author evers
 */
public interface IButtonAction<T extends IButtonContext> {
    void execute(T context);
}
