package co.edu.unicauca.domain.entities;

public class Student {

    private String nombre;
    private String cedula;
    private String codigo;
    private String email;
    private String telefono;

    public Student(String nombre, String cedula, String codigo, String email, String telefono) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.codigo = codigo;
        this.email = email;
        this.telefono = telefono;
    }

    public Student() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
