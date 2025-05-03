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
public class EnEjecucionState implements ProjectState {

    @Override
    public MessageResponse avanzarEstado(Project proyecto) {
        proyecto.setEstado(new CerradoState());  // Cambia a "CERRADO"
        return new MessageResponse(proyecto.getEstado(), "El proyecto está en ejecución y ahora está cerrado.");
    }

    @Override
    public MessageResponse NoAvanzaEstado(Project proyecto) {
        return new MessageResponse(this, "El proyecto permanece en estado EN EJECUCIÓN.");
    }

    @Override
    public String getEstado() {
        return "EN EJECUCION";
    }

    
}
