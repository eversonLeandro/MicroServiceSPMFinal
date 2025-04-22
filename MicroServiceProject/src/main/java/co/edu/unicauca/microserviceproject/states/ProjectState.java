/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.microserviceproject.states;

import co.edu.unicauca.microserviceproject.entities.Project;

/**
 *
 * @author Yisus
 */
public interface ProjectState {
    ProjectState avanzarEstado(Project proyecto);
    ProjectState NoAvanzaEstado(Project proyecto);
    String getEstado();
    }

