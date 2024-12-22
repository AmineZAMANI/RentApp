package com.common.mappers;

import org.mapstruct.Mapper;

import com.common.dtos.CarDTO;
import com.domain.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDTO toDTO(Car car);
    Car toEntity(CarDTO carDTO);
}
