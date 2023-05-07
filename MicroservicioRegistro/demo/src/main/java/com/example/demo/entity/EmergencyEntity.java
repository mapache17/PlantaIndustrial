package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "EMERGENCY")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EmergencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int sensorTemperatura;
    private int sensorPresion;
    private int sensorHumedad;
    private String hora;
    private String typeEmergency;

}
