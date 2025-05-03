/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.microserviceproject.infra.states;


import co.edu.unicauca.microserviceproject.entities.Project;


/**
 *
 * @author Yisus
 */
public class AceptadoState implements ProjectState{

    @Override
    public MessageResponse avanzarEstado(Project proyecto) {
        proyecto.setEstado(new EnEjecucionState());  // Cambia a "En Ejecución"
        return new MessageResponse(proyecto.getEstado(), "El proyecto ha sido aceptado y ahora está en ejecución.");
    }

    @Override
    public MessageResponse NoAvanzaEstado(Project proyecto) {
        return new MessageResponse(this, "El proyecto permanece en estado ACEPTADO.");
    }

    @Override
    public String getEstado() {
        return "ACEPTADO";
    }
}
