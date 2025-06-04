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
public class RecibidoState implements ProjectState {

    @Override
    public ProjectState avanzarEstado(Project proyecto) {
        return new AceptadoState();
    }

    @Override
    public ProjectState noAvanzaEstado(Project proyecto) {
        return new RechazadoState();
    }

    @Override
    public String getEstado() {
        return "RECIBIDO";
    }

    @Override
    public String getMensaje() {
        return "El proyecto ha sido recibido y está en estado RECIBIDO.";
    }
}
