package co.edu.unicauca.microservicelogin.infra.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_STUDENT_CREATED = "Studentqueue";
    public static final String QUEUE_COMPANY_CREATED = "queueCompany";
    @Bean
    public Queue UsuarioCreatedQueue() {
        return new Queue(QUEUE_STUDENT_CREATED , true);
    }
    @Bean
    public Queue CompanyCreatedQueue() {
        return new Queue(QUEUE_COMPANY_CREATED, true);
    }
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
