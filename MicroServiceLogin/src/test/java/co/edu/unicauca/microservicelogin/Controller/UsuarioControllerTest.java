package co.edu.unicauca.microservicelogin.Controller;

import co.edu.unicauca.microservicelogin.controller.UsuarioController;
import co.edu.unicauca.microservicelogin.entities.User;
import co.edu.unicauca.microservicelogin.infra.dto.UsuarioRequest;
import co.edu.unicauca.microservicelogin.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validarUsuario_ShouldReturnUserWhenExists() {
        UsuarioRequest request = new UsuarioRequest();
        request.setUsername("admin");

        User mockUser = new User("password", 1L, "ADMIN", "admin");
        when(usuarioService.findByUsername("admin")).thenReturn(Optional.of(mockUser));

        ResponseEntity<User> response = usuarioController.validarUsuario(request);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("admin", response.getBody().getUsername());
    }

    @Test
    void createUser_ShouldCreateNewUser() {
        UsuarioRequest newUser = new UsuarioRequest();
        newUser.setUsername("newuser");

        User savedUser = new User("pass", 1L, "USER", "newuser");
        when(usuarioService.save(any(UsuarioRequest.class))).thenReturn(savedUser);

        ResponseEntity<User> response = usuarioController.createUser(newUser);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("newuser", response.getBody().getUsername());
    }

    @Test
    void getAllUsuarios_ShouldReturnUserList() throws Exception {
        List<User> mockUsers = Arrays.asList(
                new User("pass1", 1L, "ADMIN", "admin"),
                new User("pass2", 2L, "USER", "user1")
        );
        when(usuarioService.findAll()).thenReturn(mockUsers);

        ResponseEntity<List<User>> response = usuarioController.getAllUsuarios();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }
}