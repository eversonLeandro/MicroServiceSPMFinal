package co.edu.unicauca.microservicepostulation.service;

import co.edu.unicauca.microservicepostulation.dto.PostulationDTO;
import co.edu.unicauca.microservicepostulation.entity.Postulation;
import co.edu.unicauca.microservicepostulation.repository.PostulationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Timestamp;
@Service
@RequiredArgsConstructor
public class PostulationService {
    @Autowired
    private PostulationRepository postulationRepository;
    @Autowired
    private SenderService senderService;

    public Postulation savePostulation(Long idEstudiante, Long idProyecto) {
        // 1. Guardar en la base de datos
        Postulation postulation = Postulation.builder()
                .idEstudiante(idEstudiante)
                .idProyecto(idProyecto)
                .fechaPostulacion(LocalDateTime.now())
                .build();

        postulation = postulationRepository.save(postulation);

        // 2. Enviar mensaje a RabbitMQ
        PostulationDTO dto = new PostulationDTO(
                idEstudiante.toString(),
                idProyecto,
                Timestamp.valueOf(postulation.getFechaPostulacion())
        );
        senderService.sendPostulation(dto);

        return postulation;
    }
}
