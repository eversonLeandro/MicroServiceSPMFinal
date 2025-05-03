package co.edu.unicauca.microservicelogin.Service;

import co.edu.unicauca.microservicelogin.entities.User;
import co.edu.unicauca.microservicelogin.infra.dto.UsuarioRequest;
import co.edu.unicauca.microservicelogin.repository.UsuarioRepository;
import co.edu.unicauca.microservicelogin.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByUsernameAndContrasenia_ShouldReturnUser() {
        User mockUser = new User("password", 1L, "ADMIN", "admin");
        when(repository.findByUsernameAndContrasenia("admin", "password"))
                .thenReturn(Optional.of(mockUser));

        Optional<User> result = usuarioService.findByUsernameAndContrasenia("admin", "password");

        assertTrue(result.isPresent());
        assertEquals("admin", result.get().getUsername());
    }

    @Test
    void findByUsername_ShouldReturnUser() {
        User mockUser = new User("password", 1L, "ADMIN", "admin");
        when(repository.findByUsername("admin")).thenReturn(Optional.of(mockUser));

        Optional<User> result = usuarioService.findByUsername("admin");

        assertTrue(result.isPresent());
        assertEquals("admin", result.get().getUsername());
    }

    @Test
    void save_ShouldCreateNewUser() {
        UsuarioRequest request = new UsuarioRequest();
        request.setUsername("newuser");
        request.setContrasenia("password");

        User userEntity = new User("password", 1L, "USER", "newuser");

        when(modelMapper.map(request, User.class)).thenReturn(userEntity);
        when(repository.save(any(User.class))).thenReturn(userEntity);

        User result = usuarioService.save(request);

        assertNotNull(result);
        assertEquals("newuser", result.getUsername());
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void findAll_ShouldReturnAllUsers() throws Exception {
        List<User> mockUsers = Arrays.asList(
                new User("pass1", 1L, "ADMIN", "admin"),
                new User("pass2", 2L, "USER", "user1")
        );
        when(repository.findAll()).thenReturn(mockUsers);

        List<User> result = usuarioService.findAll();

        assertEquals(2, result.size());
    }
}
