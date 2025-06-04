package co.edu.unicauca.microservicelogin.Service;
import co.edu.unicauca.microservicelogin.entities.User;
import co.edu.unicauca.microservicelogin.infra.config.RabbitMQConfig;
import co.edu.unicauca.microservicelogin.infra.dto.UsuarioRequest;
import co.edu.unicauca.microservicelogin.repository.UsuarioRepository;
import co.edu.unicauca.microservicelogin.service.UsuarioConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;

class UsuarioConsumerServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UsuarioConsumerService usuarioConsumerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void StudentCreated_ShouldSaveUser() {
        UsuarioRequest request = new UsuarioRequest();
        request.setUsername("student1");

        User userEntity = new User("pass", 1L, "STUDENT", "student1");

        when(modelMapper.map(request, User.class)).thenReturn(userEntity);
        when(usuarioRepository.save(any(User.class))).thenReturn(userEntity);

        usuarioConsumerService.StudentCreated(request);

        verify(usuarioRepository, times(1)).save(any(User.class));
    }

    @Test
    void CompanyCreated_ShouldSaveUser() {
        UsuarioRequest request = new UsuarioRequest();
        request.setUsername("company1");

        User userEntity = new User("pass", 1L, "COMPANY", "company1");

        when(modelMapper.map(request, User.class)).thenReturn(userEntity);
        when(usuarioRepository.save(any(User.class))).thenReturn(userEntity);

        usuarioConsumerService.CompanyCreated(request);

        verify(usuarioRepository, times(1)).save(any(User.class));
    }
}
