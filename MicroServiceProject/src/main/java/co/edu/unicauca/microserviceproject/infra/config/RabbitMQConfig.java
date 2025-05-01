package co.edu.unicauca.microserviceproject.infra.config;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMQConfig {

    public static final String PROJECT_QUEUE = "projectQueue";
    public static final String POSTULATION_QUEUE = "postulationQueue";

    @Bean
    public Queue projectQueue() {
        return new Queue(PROJECT_QUEUE , true);
    }
    @Bean
    public Queue postulationQueue() {
        return new Queue(POSTULATION_QUEUE , false);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
