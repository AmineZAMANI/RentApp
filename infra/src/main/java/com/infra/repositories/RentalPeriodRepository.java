package com.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.RentalPeriod;

public interface RentalPeriodRepository extends JpaRepository<RentalPeriod, Long> {
}