package co.edu.unicauca.studentmicroservice.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTeam;

    @ManyToMany
    @JoinTable(
            name = "team_student",
            joinColumns = @JoinColumn(name = "id_team"),
            inverseJoinColumns = @JoinColumn(name = "id_student")
    )
    private List<Student> students;

    public Team() {
    }

    public Team(List<Student> students) {
        if (students.size() > 4) {
            throw new IllegalArgumentException("Un equipo no puede tener más de 4 estudiantes.");
        }
        this.students = students;
    }

    public Long getIdTeam() {
        return idTeam;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        if (students.size() > 4) {
            throw new IllegalArgumentException("Un equipo no puede tener más de 4 estudiantes.");
        }
        this.students = students;
    }

    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }
}
