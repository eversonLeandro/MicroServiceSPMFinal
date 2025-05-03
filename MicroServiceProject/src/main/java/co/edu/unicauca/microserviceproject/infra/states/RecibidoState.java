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
public class RecibidoState implements ProjectState{

    @Override
    public MessageResponse avanzarEstado(Project proyecto) {
        proyecto.setEstado(new AceptadoState());
        return new MessageResponse(proyecto.getEstado(), "El proyecto ha sido recibido y ahora está ACEPTADO.");
    }

    @Override
    public MessageResponse NoAvanzaEstado(Project proyecto) {
        proyecto.setEstado(new RechazadoState());
        return new MessageResponse(proyecto.getEstado(), "El proyecto ha sido rechazado.");
    }

    @Override
    public String getEstado() {
        return "RECIBIDO";
    }
  

   
}
