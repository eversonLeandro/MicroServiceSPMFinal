package co.edu.unicauca.companymicroservice.Infra.Config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PROJECT_QUEUE = "projectQueue";
    public static final String QUEUE_COMPANY_CREATED = "queueCompany";
    public static final String COMPANY_QUEUE = "companyQueue";


    @Bean
    public Queue CompanyCreatedQueue() {
        return new Queue(QUEUE_COMPANY_CREATED, true);
    }
    @Bean
    public Queue projectQueue() {
        return new Queue(PROJECT_QUEUE , false);
    }
    @Bean
    public Queue companyQueue() {
        return new Queue(COMPANY_QUEUE , false);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
