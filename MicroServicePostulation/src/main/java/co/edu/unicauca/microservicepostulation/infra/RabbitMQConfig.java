package co.edu.unicauca.microservicepostulation.infra;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String POSTULATION_QUEUE = "postulation_queue";

    @Bean
    public Queue queue() {
        return new Queue(POSTULATION_QUEUE, false);
    }
}
