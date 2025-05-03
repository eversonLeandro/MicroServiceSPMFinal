/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author RoLoNeGaTiVo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Postulation {

    private Long idPostulation;
    private Long idEstudiante;
    private Long idProyecto;
    private transient Timestamp fechaPostulacion;

    public Postulation(Long idEstudiante, Long idProyecto) {
        this.idEstudiante = idEstudiante;
        this.idProyecto = idProyecto;
    }

    public Postulation() {

    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Timestamp getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(Timestamp fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    public Long getIdPostulation() {
        return idPostulation;
    }

    public void setIdPostulation(Long idPostulation) {
        this.idPostulation = idPostulation;
    }
}
