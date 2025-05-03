package co.edu.unicauca.studentmicroservice.Infra.Config;

import co.edu.unicauca.studentmicroservice.infra.config.RabbitMQConfig;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("test")
public class TestRabbitMQConfig {
    
    @Bean
    public Queue usuarioCreatedQueue() {
        return new Queue(RabbitMQConfig.QUEUE_STUDENT_CREATED, false);
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate();
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }
}