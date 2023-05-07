package com.example.demo.controller;

import com.example.demo.service.rabbitMQ.ServiceSendSensorValues;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.annotation.Scheduled;

@RestController
public class ProducerPlantaController {

    @Autowired
    private ServiceSendSensorValues serviceSendSensorValues;
    @Scheduled(fixedRate = 10000)
    @GetMapping(path="/planta-sensores")
    public void enviarDatosSensores () throws JsonProcessingException {
        System.out.println("Hola");
        serviceSendSensorValues.enviaSensorValues();
    }
}
