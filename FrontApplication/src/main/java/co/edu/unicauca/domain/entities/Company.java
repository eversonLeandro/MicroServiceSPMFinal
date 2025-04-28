/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.domain.entities;


/**
 *
 * @author Brayan
 */
public class Company {

    private String nit;
    private String nombre;
    private String email;
    private String telefono;
    private String nombrecontaccto;
    private String apellido;
    private String sector;
    private String cargo;
    private String estado;
    public Company() {
    }

    public Company(String nit, String nombre, String email, String telefono, String nombrecontaccto, String apellido, String sector, String cargo,String estado) {
        this.nit = nit;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.nombrecontaccto = nombrecontaccto;
        this.apellido = apellido;
        this.sector = sector;
        this.cargo = cargo;
        this.estado= estado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNit() {
        return nit;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombrecontaccto() {
        return nombrecontaccto;
    }

    public String getApellido() {
        return apellido;
    }

    public String getSector() {
        return sector;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setNombrecontaccto(String nombrecontaccto) {
        this.nombrecontaccto = nombrecontaccto;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
