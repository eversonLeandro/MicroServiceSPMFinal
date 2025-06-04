package co.edu.unicauca.studentmicroservice.infra.dto;

public class UsuarioRequest {
    private long id;
    private String username;
    private String contrasenia;
    private String rol;
    private String email;

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

}
