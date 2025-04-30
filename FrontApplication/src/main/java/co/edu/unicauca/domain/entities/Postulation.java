/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.domain.entities;

import java.sql.Timestamp;

/**
 *
 * @author RoLoNeGaTiVo
 */
public class Postulation {

    private String codStudent;
    private String codProject;
    private Timestamp fecha;

    public Postulation(String codStudent_,String codProject_, Timestamp fecha_) {
        this.codStudent = codStudent_;
        this.codProject = codProject_;
        this.fecha = fecha_;
    }

    /**
     * @return the codStudent
     */
    public String getCodStudent() {
        return codStudent;
    }

    /**
     * @param codStudent the codStudent to set
     */
    public void setCodStudent(String codStudent) {
        this.codStudent = codStudent;
    }

    /**
     * @return the codProject
     */
    public String getCodProject() {
        return codProject;
    }

    /**
     * @param codProject the codProject to set
     */
    public void setCodProject(String codProject) {
        this.codProject = codProject;
    }

    /**
     * @return the fecha
     */
    public Timestamp getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
