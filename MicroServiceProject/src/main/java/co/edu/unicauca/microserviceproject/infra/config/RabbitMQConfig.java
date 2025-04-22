package co.edu.unicauca.microserviceproject.infra.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMQConfig {

    public static final String PROJECT_QUEUE = "projectQueue";

    @Bean
    public Queue projectQueue() {
        return new Queue(PROJECT_QUEUE , true);
    }


}
