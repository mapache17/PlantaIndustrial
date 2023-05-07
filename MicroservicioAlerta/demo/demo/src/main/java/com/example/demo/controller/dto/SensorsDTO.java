package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class SensorsDTO {

    private int id;
    private int sensorTemperatura;
    private int sensorPresion;
    private int sensorHumedad;
    private String hora;

}
