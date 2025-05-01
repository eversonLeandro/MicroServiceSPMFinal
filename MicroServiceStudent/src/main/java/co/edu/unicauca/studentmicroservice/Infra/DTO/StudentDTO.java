package co.edu.unicauca.studentmicroservice.Infra.DTO;

public class StudentDTO {
    private String codEst;
    private String cedula;
    private String telefono;
    private String userName;
    private String email;

    public StudentDTO() {}

    public StudentDTO(String codEst, String cedula, String telefono, String userName,String email) {
        this.codEst = codEst;
        this.cedula = cedula;
        this.telefono = telefono;
        this.userName = userName;
        this.email = email;
    }

    public String getCodEst() {
        return codEst;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCodEst(String codEst) {
        this.codEst = codEst;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
