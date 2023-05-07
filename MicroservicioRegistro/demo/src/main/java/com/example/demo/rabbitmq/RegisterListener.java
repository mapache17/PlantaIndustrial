package com.example.demo.rabbitmq;

import com.example.demo.rabbitmq.dto.SensorsDTO;
import com.example.demo.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegisterListener {
    RegisterService registerService;
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(conversor());
        return rabbitTemplate;
    }
    @Bean
    public Jackson2JsonMessageConverter conversor(){
        return new Jackson2JsonMessageConverter ();
    }

    @RabbitListener(queues = {"filaRegistros"})
    public void saveAllOperations(SensorsDTO sensorsDTO)
    {
        registerService.saveAllOperations(sensorsDTO);
        registerService.saveEmergencies(sensorsDTO);

    }
}
