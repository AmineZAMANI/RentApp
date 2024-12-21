package com.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}

