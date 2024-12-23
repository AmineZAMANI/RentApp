package com.web.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.application.services.CarApplicationService;
import com.common.dtos.CarDTO;
import com.common.mappers.CarMapper;
import com.domain.Car;

@Service
public class CarWebService {

	private final CarMapper carMapper;
	private final CarApplicationService carApplicationService;

	public CarWebService(CarMapper carMapper, CarApplicationService carApplicationService) {
		this.carMapper = carMapper;
		this.carApplicationService = carApplicationService;
	}

	public List<CarDTO> findCar(String model) {
		List<Car> cars = carApplicationService.searchAvailableCars(model);
		return carMapper.toDTOs(cars);
	}

}
