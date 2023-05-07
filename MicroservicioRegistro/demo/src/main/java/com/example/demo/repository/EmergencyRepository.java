package com.example.demo.repository;

import com.example.demo.entity.EmergencyEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public interface EmergencyRepository extends JpaRepository<EmergencyEntity,Long> {
}
