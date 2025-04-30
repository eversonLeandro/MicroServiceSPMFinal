/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.microserviceproject.states;

import co.edu.unicauca.microserviceproject.entities.Project;
/**
 *
 * @author Yisus
 */
public class EnEjecucionState implements ProjectState {

    @Override
    public ProjectState avanzarEstado(Project proyecto) {
        proyecto.setEstado(new CerradoState());
        return proyecto.getEstado();
    }

    @Override
    public ProjectState NoAvanzaEstado(Project proyecto) {
        return proyecto.getEstado();
    }
    
    @Override
    public String getEstado() {
        return "EJECUCION";
    }

 

    
}
