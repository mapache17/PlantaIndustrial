package com.example.demo.rabbitmq;

import com.example.demo.controller.dto.SensorsDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AlertaListener {

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

    @RabbitListener(queues = {"filaSensores"})
    public void verificarSensores(SensorsDTO sensorsDto){
        System.out.println("Estoy recibiendo datos de sensores, hora:"+ sensorsDto.getHora());
        if (sensorsDto.getSensorTemperatura() <80 && sensorsDto.getSensorTemperatura() >20){
            System.out.println("Director planta, el nivel de temperatura se encuentra estable, el valor es "+ sensorsDto.getSensorTemperatura());
        }
        else{
            System.out.print("Director planta, hay una anomalia en la temperatura de la planta, el valor es "+ sensorsDto.getSensorTemperatura() +" grados, por favor verificar");
        }

        if (sensorsDto.getSensorPresion() <300 && sensorsDto.getSensorPresion() >100){
            System.out.print("; el nivel de presion se encuentra estable, el valor es "+ sensorsDto.getSensorPresion() +" pascales");
        }
        else{
            System.out.print("; hay una anomalia en la presion de la planta, el valor es "+ sensorsDto.getSensorPresion() +" pascales, por favor verificar");
        }

        if (sensorsDto.getSensorHumedad() <70 && sensorsDto.getSensorHumedad() >0){
            System.out.print("; el nivel de humedad se encuentra estable, el valor es "+ sensorsDto.getSensorHumedad());
            System.out.println(" ");

        }
        else{
            System.out.print("; hay una anomalia en el nivel de humedad de la planta, el valor es "+ sensorsDto.getSensorHumedad() +", por favor verificar");
            System.out.println(" ");

        }
    }
}