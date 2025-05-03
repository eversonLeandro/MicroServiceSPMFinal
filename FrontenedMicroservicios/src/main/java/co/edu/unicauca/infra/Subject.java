/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.infra;

import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.interfaces.IProjectObserver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yisus
 */
public abstract class Subject {
    
    private final List<IProjectObserver> observadores = new ArrayList<>();
    private static final Subject instance = new Subject() {};
    
    public static Subject getInstance(){
        return instance;
    }
    public void agregarObservador(IProjectObserver observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(IProjectObserver observador) {
        observadores.remove(observador);
    }

    protected void notificarObservadores(List<Project> proyectos) {
        for (IProjectObserver obs : observadores) {
            obs.actualizarProyectos(proyectos);
        }
    }
}
