package co.edu.unicauca.studentmicroservice.Infra.Mappers;

import co.edu.unicauca.studentmicroservice.Infra.DTO.TeamDTO;
import co.edu.unicauca.studentmicroservice.Entities.Team;
import co.edu.unicauca.studentmicroservice.Entities.Student;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {

    public static TeamDTO toDTO(Team team) {
        TeamDTO dto = new TeamDTO();
        dto.setIdTeam(team.getIdTeam());

        if (team.getStudents() != null) {
            List<String> studentCodes = team.getStudents()
                    .stream()
                    .map(Student::getCodEst)
                    .collect(Collectors.toList());
            dto.setStudentCodes(studentCodes);
        }

        return dto;
    }

    public static Team toEntity(TeamDTO dto, List<Student> students) {
        Team team = new Team();
        team.setStudents(students);
        return team;
    }
}
