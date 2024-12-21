package com.application.mappers;

import org.mapstruct.Mapper;

import com.application.dtos.CarDTO;
import com.domain.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDTO toDTO(Car car);
    Car toEntity(CarDTO carDTO);
}
