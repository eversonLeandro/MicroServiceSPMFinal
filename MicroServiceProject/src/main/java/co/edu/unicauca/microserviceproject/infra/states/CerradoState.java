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
public class CerradoState implements ProjectState{

    @Override
    public MessageResponse avanzarEstado(Project proyecto) {
        return new MessageResponse(this, "El proyecto está cerrado y no puede avanzar.");
    }

    @Override
    public MessageResponse NoAvanzaEstado(Project proyecto) {
        return new MessageResponse(this, "El proyecto permanece en estado CERRADO.");
    }

    @Override
    public String getEstado() {
        return "CERRADO";
    }
  
}
