package com.infra.entity.mappers;

import org.mapstruct.Mapper;

import com.domain.Car;
import com.infra.entity.CarEntity;

@Mapper(componentModel = "spring")
public interface CarEntityMapper {
    Car toModel(CarEntity car);
    CarEntity toEntity(Car carEntity);
}
