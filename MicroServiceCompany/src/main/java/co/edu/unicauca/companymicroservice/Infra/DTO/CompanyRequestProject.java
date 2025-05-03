package co.edu.unicauca.companymicroservice.Infra.DTO;

public class CompanyRequestProject {
    private Long nit;
    private String nombre;

    public CompanyRequestProject() {

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
