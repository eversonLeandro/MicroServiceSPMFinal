package co.edu.unicauca.companymicroservice.Entities;

import jakarta.persistence.*;

import jakarta.persistence.*;

@Entity
@Table(name = "contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContacto")
    private Long idContacto;

    @Column(length = 50, nullable = false)
    private String apellido;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 15, nullable = false)
    private String telefono;

    @Column(length = 50, nullable = false)
    private String cargo;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    public Contacto() {
    }

    public Contacto(String apellido, String nombre, String telefono,String cargo,Company company) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.cargo = cargo;
        this.company = company;
    }


    public Long getIdContacto() {
        return idContacto;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setCompany(Company company){
        this.company = company;
    }
    public Company getCompany(){
        return this.company;
    }

}
