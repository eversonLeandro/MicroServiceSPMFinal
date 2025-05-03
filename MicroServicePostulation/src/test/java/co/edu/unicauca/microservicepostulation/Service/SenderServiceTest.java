package co.edu.unicauca.microservicepostulation.Service;

import co.edu.unicauca.microservicepostulation.dto.PostulationDTO;
import co.edu.unicauca.microservicepostulation.infra.RabbitMQConfig;
import co.edu.unicauca.microservicepostulation.service.SenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SenderServiceTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private SenderService senderService;

    private PostulationDTO postulationDTO;
    private Timestamp testTimestamp;

    @BeforeEach
    void setUp() {
        testTimestamp = new Timestamp(System.currentTimeMillis());
        postulationDTO = new PostulationDTO(1L, "100", 200L, testTimestamp);
    }

    @Test
    void sendPostulation_WithTimestamp() {
        senderService.sendPostulation(postulationDTO);
        verify(rabbitTemplate, times(1))
            .convertAndSend(
                eq(RabbitMQConfig.POSTULATION_QUEUE), 
                eq(postulationDTO)
            );
        assertEquals(testTimestamp, postulationDTO.getFecha());
    }
}