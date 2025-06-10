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
public class CerradoState implements ProjectState {

    @Override
    public ProjectState avanzarEstado(Project proyecto) {
        return this; // no avanza más
    }

    @Override
    public ProjectState noAvanzaEstado(Project proyecto) {
        return this;
    }

    @Override
    public String getEstado() {
        return "CERRADO";
    }

    @Override
    public String getMensaje() {
        return "El proyecto ahora se encuentra CERRADO";
    }

}
