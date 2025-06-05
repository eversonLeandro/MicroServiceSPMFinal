package co.edu.unicauca.microserviceemailnotification.infra.config;

import co.edu.unicauca.microserviceemailnotification.infra.dto.NotificationStatus;
import co.edu.unicauca.microserviceemailnotification.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = RabbitMQConfig.PROJECT_STATUS_NOTIFICATION_QUEUE)
    public void recibirNotificacion(NotificationStatus dto) {
        System.out.println("📩 Mensaje recibido en la cola:");
        System.out.println("➡️ Proyecto: " + dto.getNombreProyecto());
        System.out.println("➡️ Estado: " + dto.getEstado());
        System.out.println("➡️ Correo empresa: " + dto.getCorreoEmpresa());
        System.out.println("➡️ Correo coordinador: " + dto.getCorreoCordinador());

       //emailService.enviarCorreo(dto);
        System.out.println("📩 Mensaje recibido en la cola: " + dto);
    }
}
