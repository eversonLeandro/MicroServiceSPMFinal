/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.microserviceproject.domain.model.project.state;


import co.edu.unicauca.microserviceproject.domain.model.project.Project;

/**
 *
 * @author everson
 */
public class AceptadoState implements ProjectState {

    @Override
    public ProjectState avanzarEstado(Project proyecto) {
        return new EnEjecucionState();
    }

    @Override
    public ProjectState noAvanzaEstado(Project proyecto) {
        return this; // no cambia
    }

    @Override
    public String getEstado() {
        return "ACEPTADO";
    }

    @Override
    public String getMensaje() {
        return "El proyecto ha sido ACEPTADO.";
    }
}