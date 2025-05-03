package co.edu.unicauca.studentmicroservice.Service;

import co.edu.unicauca.studentmicroservice.Entities.Student;
import co.edu.unicauca.studentmicroservice.Entities.Team;
import co.edu.unicauca.studentmicroservice.Infra.DTO.TeamDTO;
import co.edu.unicauca.studentmicroservice.Repositories.StudentRepository;
import co.edu.unicauca.studentmicroservice.Repositories.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private TeamService teamService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll_Success() throws Exception {
        Team team1 = new Team();
        Team team2 = new Team();
        List<Team> teams = Arrays.asList(team1, team2);
        
        when(teamRepository.findAll()).thenReturn(teams);

        List<TeamDTO> result = teamService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testFindAll_Exception() {
        when(teamRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        assertThrows(Exception.class, () -> teamService.findAll());
    }

    @Test
    void testFindById_Success() throws Exception {
        String teamId = "1";
        Team team = new Team();
        team.setIdTeam(1L);
        
        when(teamRepository.findById(Long.valueOf(teamId))).thenReturn(Optional.of(team));

        TeamDTO result = teamService.findById(teamId);

        assertNotNull(result);
        assertEquals(team.getIdTeam(), result.getIdTeam());
    }

    @Test
    void testFindById_NotFound() {
        String teamId = "999";
        when(teamRepository.findById(Long.valueOf(teamId))).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> teamService.findById(teamId));
    }

    @Test
    void testSave_Success() throws Exception {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setIdTeam(1L);
        teamDTO.setStudentCodes(Arrays.asList("20201", "20202"));
        
        Student student1 = new Student("123", "20201", "test1@email.com", "Student 1", "123456");
        Student student2 = new Student("456", "20202", "test2@email.com", "Student 2", "654321");
        
        when(studentRepository.findById(Long.valueOf("20201"))).thenReturn(Optional.of(student1));
        when(studentRepository.findById(Long.valueOf("20202"))).thenReturn(Optional.of(student2));
        when(teamRepository.save(any(Team.class))).thenReturn(new Team());

        boolean result = teamService.save(teamDTO);

        assertTrue(result);
    }
}