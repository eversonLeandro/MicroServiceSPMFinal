package co.edu.unicauca.microserviceemailnotification.service;

import co.edu.unicauca.microserviceemailnotification.infra.dto.NotificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreo(NotificationStatus dto) {
         if (dto.getCorreoEmpresa() == null || dto.getCorreoEmpresa().isBlank()) {
            System.err.println("❌ El correo de la empresa es nulo o vacío. No se puede enviar el email.");
            return;
        }

        String asunto = "Actualización del estado de tu proyecto";

        String mensaje = "Hola,\n\n" +
                "Queremos informarte que el estado de tu proyecto ha cambiado.\n\n" +
                "Nombre del proyecto: " + dto.getNombreProyecto() + "\n" +
                "Fecha de actualización: " + LocalDate.now() + "\n" +
                "Nuevo estado: " + dto.getEstado() + "\n\n" +
                "Puedes ingresar al sistema para revisar más detalles.\n\n" +
                "Gracias por usar nuestra plataforma.\n\n" +
                "Atentamente,\n" +
                "Equipo de Gestión de Proyectos - Unicauca";

        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(dto.getCorreoEmpresa());
            email.setSubject(asunto);
            email.setText(mensaje);
            // Opcional: puedes incluir un reply-to con el correo del coordinador si es válido
            if (dto.getCorreoCordinador() != null && !dto.getCorreoCordinador().isBlank()) {
                email.setReplyTo(dto.getCorreoCordinador());
            }

            mailSender.send(email);
            System.out.println(" Correo enviado a " + dto.getCorreoEmpresa());

        } catch (Exception e) {
            System.err.println(" Error al enviar el correo: " + e.getMessage());
        }
    }
}
