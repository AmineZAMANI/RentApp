package com.common.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.common.dtos.CarDTO;
import com.domain.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {
	@Mapping(source = "id", target = "id")
	@Mapping(source = "model", target = "model")
	@Mapping(source = "available", target = "available")
	CarDTO toDTO(Car car);
	
	
	List<CarDTO> toDTOs(List<Car> cars);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "model", target = "model")
	@Mapping(source = "available", target = "available")
	Car toDomain(CarDTO carDTO);
	
	List<Car> toDomains(List<CarDTO> carsDtos);
}
