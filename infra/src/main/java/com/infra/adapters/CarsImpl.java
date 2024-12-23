package com.infra.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.domain.Car;
import com.domain.repositories.Cars;
import com.infra.entity.CarEntity;
import com.infra.entity.mappers.CarEntityMapper;
import com.infra.repositories.CarRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@AllArgsConstructor
public class CarsImpl implements Cars {

    private final CarRepository carJpaRepository;
    private final CarEntityMapper carEntityMapper;

    @Override
    public List<Car> searchAvailable(String carModel, Boolean available) {
        List<CarEntity> availableCars = carJpaRepository.findByModelAndAvailable(carModel, available);
        return availableCars.stream().map(carEntity -> carEntityMapper.toModel(carEntity)).collect(Collectors.toList());
    }

}
