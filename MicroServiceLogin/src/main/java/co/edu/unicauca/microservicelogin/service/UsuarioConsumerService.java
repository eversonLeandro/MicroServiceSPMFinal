package co.edu.unicauca.microservicelogin.service;

import co.edu.unicauca.microservicelogin.entities.User;
import co.edu.unicauca.microservicelogin.infra.config.RabbitMQConfig;
import co.edu.unicauca.microservicelogin.infra.dto.UsuarioRequest;
import co.edu.unicauca.microservicelogin.repository.UsuarioRepository;
import jakarta.ws.rs.ClientErrorException;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UsuarioConsumerService {

    @Autowired
    private UsuarioService usuarioService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_STUDENT_CREATED)
    public void StudentCreated(UsuarioRequest usuarionuevo) {
        System.out.println("Usuario recibido desde cola: " + usuarionuevo);
        System.out.println("Rol recibido desde cola: " + usuarionuevo.getRol());

        try {
            usuarioService.save(usuarionuevo);
            System.out.println("Usuario registrado desde Student: " + usuarionuevo.getUsername());
        }catch(Exception e) {
            System.err.println(" Error al crear usuario desde RabbitMQ: " + e.getMessage());
        }
    }
    @RabbitListener(queues = RabbitMQConfig.QUEUE_COMPANY_CREATED)
    public void CompanyCreated(UsuarioRequest companynuevo) {
        try {
            usuarioService.save(companynuevo);
            System.out.println("Usuario registrado desde Company: " + companynuevo.getUsername());
        }catch(Exception e) {
            System.err.println(" Error al crear usuario desde RabbitMQ: " + e.getMessage());
        }
    }

}
