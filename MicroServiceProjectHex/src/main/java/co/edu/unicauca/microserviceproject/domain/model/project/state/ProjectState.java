/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.microserviceproject.domain.model.project.state;


import co.edu.unicauca.microserviceproject.domain.model.project.Project;

/**
 *
 * @author everson
 */
public interface ProjectState {

    ProjectState avanzarEstado(Project proyecto);
    ProjectState noAvanzaEstado(Project proyecto);

    String getEstado();
    String getMensaje();
    }

