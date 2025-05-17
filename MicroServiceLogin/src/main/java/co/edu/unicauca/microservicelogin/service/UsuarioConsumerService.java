package co.edu.unicauca.microservicelogin.service;

import co.edu.unicauca.microservicelogin.entities.User;
import co.edu.unicauca.microservicelogin.infra.config.RabbitMQConfig;
import co.edu.unicauca.microservicelogin.infra.dto.UsuarioRequest;
import co.edu.unicauca.microservicelogin.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UsuarioConsumerService {
    ModelMapper modelMapper= new ModelMapper();
    @Autowired
    UsuarioRepository usuarioRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_STUDENT_CREATED)
    public void StudentCreated(UsuarioRequest usuarionuevo) {
        User user=modelMapper.map(usuarionuevo, User.class);
        usuarioRepository.save(user);
        System.out.println("usuario registrado: "+usuarionuevo.getUsername());
    }
    @RabbitListener(queues = RabbitMQConfig.QUEUE_COMPANY_CREATED)
    public void CompanyCreated(UsuarioRequest companynuevo) {
        User user=modelMapper.map(companynuevo, User.class);
        usuarioRepository.save(user);
        System.out.println("usuario registrado: "+companynuevo.getUsername());
    }

}
