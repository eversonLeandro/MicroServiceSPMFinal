package co.edu.unicauca.microservicelogin.Repository;

import co.edu.unicauca.microservicelogin.entities.User;
import co.edu.unicauca.microservicelogin.infra.dto.UsuarioRequest;
import co.edu.unicauca.microservicelogin.repository.DataLoader;
import co.edu.unicauca.microservicelogin.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class DataLoaderTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private DataLoader dataLoader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_ShouldCreateInitialUsers() throws Exception {
        when(usuarioService.findAll()).thenReturn(List.of());

        dataLoader.run();

        verify(usuarioService, atLeast(3)).save(any(UsuarioRequest.class));
    }

    @Test
    void run_ShouldNotCreateUsersWhenAlreadyExist() throws Exception {
        UsuarioRequest existingUser = new UsuarioRequest();
        existingUser.setUsername("admin");
        when(usuarioService.findAll()).thenReturn(List.of(new User()));

        dataLoader.run();

        verify(usuarioService, never()).save(any(UsuarioRequest.class));
    }
}