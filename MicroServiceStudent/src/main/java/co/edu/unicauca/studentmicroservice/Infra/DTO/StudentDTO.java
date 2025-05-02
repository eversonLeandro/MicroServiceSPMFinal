package co.edu.unicauca.studentmicroservice.Infra.DTO;

public class StudentDTO {
    private String codEst;
    private String cedula;
    private String telefono;
    private String userName;

    public StudentDTO() {}

    public StudentDTO(String codEst, String cedula, String telefono, String userName) {
        this.codEst = codEst;
        this.cedula = cedula;
        this.telefono = telefono;
        this.userName = userName;
    }

    public String getCodEst() { return codEst; }
    public void setCodEst(String codEst) { this.codEst = codEst; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
}
