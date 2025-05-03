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
public class RechazadoState implements ProjectState {

    @Override
    public MessageResponse avanzarEstado(Project proyecto) {
        // El proyecto ha sido rechazado y no puede avanzar
        return new MessageResponse(this, "El proyecto ha sido rechazado y no puede avanzar.");
    }

    @Override
    public MessageResponse NoAvanzaEstado(Project proyecto) {
        // El estado no avanza, permanece en "RECHAZADO"
        return new MessageResponse(this, "El proyecto está en estado RECHAZADO y no avanza.");
    }

    @Override
    public String getEstado() {
        return "RECHAZADO";
    }
}
