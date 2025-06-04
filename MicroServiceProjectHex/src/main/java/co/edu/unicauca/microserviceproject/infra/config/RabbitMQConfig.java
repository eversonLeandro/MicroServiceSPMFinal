package co.edu.unicauca.microserviceproject.infra.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String PROJECT_QUEUE = "projectQueue";
    public static final String POSTULATION_QUEUE = "postulationQueue";
    public static final String COMPANY_QUEUE = "companyQueue";
    public static final String PROJECT_STATUS_NOTIFICATION_QUEUE = "projectStatusNotificationQueue";


    @Bean
    public Queue projectQueue() {
        return new Queue(PROJECT_QUEUE, false);
    }

    @Bean
    public Queue postulationQueue() {
        return new Queue(POSTULATION_QUEUE, false);
    }

    @Bean
    public Queue companyQueue() {
        return new Queue(COMPANY_QUEUE, false);
    }

    @Bean
    public Queue projectStatusNotificationQueue() {
        return new Queue(PROJECT_STATUS_NOTIFICATION_QUEUE, false);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
