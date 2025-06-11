package co.edu.unicauca.microserviceproject.infra.dto;

public class CompanyRequestProject {
    private Long nit;
    private String nombre;
    private String email;

    public CompanyRequestProject() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
