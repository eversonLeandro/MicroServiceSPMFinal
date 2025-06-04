/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.domain.entities;

import co.edu.unicauca.interfaces.ICoordinatorRepository;

/**
 *
 * @author Brayan
 */
public class Coordination {

    private String codCor;
    private String UserName;
    private String cedula;
    private String telefono;
    private String estado;
    private String gmail;
 public Coordination(){
     
 }
    public Coordination(String codCor, String UserName, String cedula, String telefono, String estado, String gmail) {
        this.codCor = codCor;
        this.UserName = UserName;
        this.cedula = cedula;
        this.telefono = telefono;
        this.estado = estado;
        this.gmail = gmail;
    }

   
    public String getCodCor() {
        return codCor;
    }

    /**
     * @param codCor the codCor to set
     */
    public void setCodCor(String codCor) {
        this.codCor = codCor;
    }

    /**
     * @return the UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * @param UserName the UserName to set
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the gmail
     */
    public String getGmail() {
        return gmail;
    }

    /**
     * @param gmail the gmail to set
     */
    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

}
