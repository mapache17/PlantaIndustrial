package com.example.demo.service.rabbitMQ;

import com.example.demo.controller.dto.SensorsDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

@Component
@EnableRabbit
@AllArgsConstructor
@NoArgsConstructor
public class ServiceSendSensorValues {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public String convertSensorsDtoToString(SensorsDTO sensorsDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(sensorsDto);
    }

    public void enviaSensorValues() throws JsonProcessingException {
        Random random =new Random();
        Date fechaActual = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaActual = formatoHora.format(fechaActual);
        SensorsDTO sensorsDto= new SensorsDTO(random.nextInt(701),random.nextInt(101),random.nextInt(301),random.nextInt(101),horaActual);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend("plantaFila","Alerta", sensorsDto);
        rabbitTemplate.convertAndSend("plantaFila","Registro", sensorsDto);
    }

    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}