package com.example.demo.service;

import com.example.demo.entity.EmergencyEntity;
import com.example.demo.entity.OperationEntity;
import com.example.demo.rabbitmq.dto.SensorsDTO;
import com.example.demo.repository.EmergencyRepository;
import com.example.demo.repository.OperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterService {

    OperationRepository operationRepository;
    EmergencyRepository emergencyRepository;
    public void saveAllOperations(SensorsDTO sensorsDTO)
    {
        operationRepository.save(new OperationEntity((long)sensorsDTO.getId(),sensorsDTO.getSensorTemperatura(),sensorsDTO.getSensorPresion(),sensorsDTO.getSensorHumedad(),sensorsDTO.getHora()));
    }
    public void saveEmergencies(SensorsDTO sensorsDTO)
    {
        if ((sensorsDTO.getSensorTemperatura() >80 || sensorsDTO.getSensorTemperatura() <20)||(sensorsDTO.getSensorPresion() >300 || sensorsDTO.getSensorPresion() <100)||(sensorsDTO.getSensorHumedad() >70 || sensorsDTO.getSensorHumedad() <0))
        {
            emergencyRepository.save(new EmergencyEntity((long)sensorsDTO.getId(),sensorsDTO.getSensorTemperatura(),sensorsDTO.getSensorPresion(),sensorsDTO.getSensorHumedad(),sensorsDTO.getHora(),"emergency that can damage machinery"));
        }
    }
}
