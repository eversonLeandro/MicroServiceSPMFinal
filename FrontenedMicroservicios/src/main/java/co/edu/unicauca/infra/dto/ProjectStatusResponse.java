/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.infra.dto;

/**
 *
 * @author evers
 */
public class ProjectStatusResponse {

    private String estado;
    private String mensaje;

    public ProjectStatusResponse(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public ProjectStatusResponse() {

    }

    public String getEstado() {
        return estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
