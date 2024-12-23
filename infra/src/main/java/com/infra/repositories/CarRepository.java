package com.infra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infra.entity.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
	 List<CarEntity> findByModelAndAvailable(String model, Boolean available);
}

