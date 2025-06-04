package co.edu.unicauca.studentmicroservice.Infra.DTO;

import java.util.List;

public class TeamDTO {
    private Long idTeam;
    private List<String> studentCodes;

    public TeamDTO() {
    }

    public TeamDTO(Long idTeam, List<String> studentCodes) {
        this.idTeam = idTeam;
        this.studentCodes = studentCodes;
    }

    public Long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }

    public List<String> getStudentCodes() {
        return studentCodes;
    }

    public void setStudentCodes(List<String> studentCodes) {
        this.studentCodes = studentCodes;
    }
}
