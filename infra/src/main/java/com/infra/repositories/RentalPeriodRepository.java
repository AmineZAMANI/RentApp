package com.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.RentalPeriod;
import com.infra.entity.RentalPeriodEntity;

public interface RentalPeriodRepository extends JpaRepository<RentalPeriodEntity, Long> {
}