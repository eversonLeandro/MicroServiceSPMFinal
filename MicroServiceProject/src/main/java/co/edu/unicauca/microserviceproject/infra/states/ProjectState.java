/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.microserviceproject.infra.states;

import co.edu.unicauca.microserviceproject.entities.Project;

/**
 *
 * @author Yisus
 */
public interface ProjectState {

    MessageResponse avanzarEstado(Project proyecto);

    MessageResponse NoAvanzaEstado(Project proyecto);

    String getEstado();
    }

