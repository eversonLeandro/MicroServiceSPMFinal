package co.edu.unicauca.studentmicroservice.Service;

import co.edu.unicauca.studentmicroservice.Entities.Student;
import co.edu.unicauca.studentmicroservice.Entities.Team;
import co.edu.unicauca.studentmicroservice.Infra.DTO.TeamDTO;
import co.edu.unicauca.studentmicroservice.Infra.Mappers.TeamMapper;
import co.edu.unicauca.studentmicroservice.Repositories.StudentRepository;
import co.edu.unicauca.studentmicroservice.Repositories.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService{

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public List<TeamDTO> findAll() throws Exception {
        try{
            List<Team> entities = teamRepository.findAll();
            return entities.stream().map(TeamMapper::toDTO).collect(Collectors.toList());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public TeamDTO findById(String id) throws Exception {
        try{
            Optional<Team> optionalTeam = teamRepository.findById(Long.valueOf(id));
            if (optionalTeam.isEmpty()) {
                throw new Exception("Equipo no encontrado con ID: " + id);
            }
            return TeamMapper.toDTO(optionalTeam.get());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public boolean save(TeamDTO entity) throws Exception {
        try{
            List<Student> students = new ArrayList<>();
            for (String code : entity.getStudentCodes()) {
                studentRepository.findById(Long.valueOf(code)).ifPresent(students::add);
            }
            if (students.size() > 4) {
                throw new IllegalArgumentException("Un equipo no puede tener más de 4 estudiantes.");
            }

            Team team = TeamMapper.toEntity(entity, students);
            teamRepository.save(team);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
