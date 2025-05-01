package co.edu.unicauca.companymicroservice.Infra.DTO;
public class CompanyDTO {
    private String nit;
    private String nombre;
    private String email;
    private String telefono;
    private String nombrecontaccto;
    private String apellido;
    private String sector;
    private String cargo;
    private String estado;


    public CompanyDTO() {
    }

    public CompanyDTO(String nit, String nombre, String email, String telefono, String nombrecontaccto, String apellido, String sector, String cargo, String estado) {
        this.nit = nit;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.nombrecontaccto = nombrecontaccto;
        this.apellido = apellido;
        this.sector = sector;
        this.cargo = cargo;
        this.estado = estado;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getNombrecontaccto() {
        return nombrecontaccto;
    }

    public void setNombrecontaccto(String nombrecontaccto) {
        this.nombrecontaccto = nombrecontaccto;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
