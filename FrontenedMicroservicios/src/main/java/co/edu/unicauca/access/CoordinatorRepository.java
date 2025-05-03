/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.access;

import co.edu.unicauca.interfaces.ICoordinatorRepository;
import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.domain.entities.User;
/*import co.edu.unicauca.domain.states.AceptadoState;
import co.edu.unicauca.domain.states.EnEjecucionState;
import co.edu.unicauca.domain.states.RechazadoState;
import co.edu.unicauca.domain.states.RecibidoState;
import co.edu.unicauca.domain.states.CerradoState;*/
import co.edu.unicauca.infra.Messages;
//import co.edu.unicauca.interfaces.ProjectState;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author Brayan
 */
public class CoordinatorRepository implements ICoordinatorRepository {
    private Connection conn;
    private static final String url = "jdbc:mysql://localhost:3306/gestion_proyectos_software";
    private static final String user = "root"; // Cambia si usas otro usuario
    private static final String password = "oracle"; // Cambia por tu contraseña

    public CoordinatorRepository() {
       
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean save(Object entity) {
        return true;
    }

    @Override
    public boolean update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Object> list() {

      return null;
    }

    @Override
    public User found(Object username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

/*
    private ProjectState obtenerEstadoDesdeBD(String estadoBD) {
        switch (estadoBD.trim().toUpperCase()) {
            case "ACEPTADO":
                return new AceptadoState();
            case "EN EJECUCION":
                return new EnEjecucionState();
            case "RECHAZADO":
                return (ProjectState) new RechazadoState();
            case "RECIBIDO":
                return new RecibidoState();
            case "CERRADO":
                return new CerradoState();
            default:
                throw new IllegalArgumentException("Estado no reconocido: " + estadoBD);
        }
    }
*/
    private boolean conectar(){
       return false;
       
    }

   
}
