package com.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.Car;
import com.infra.entity.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
}

