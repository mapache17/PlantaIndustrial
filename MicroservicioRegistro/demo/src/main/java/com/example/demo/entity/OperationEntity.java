package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "OPERATION")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OperationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int sensorTemperatura;
    private int sensorPresion;
    private int sensorHumedad;
    private String hora;

}
