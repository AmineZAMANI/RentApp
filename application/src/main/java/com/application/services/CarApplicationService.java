package com.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.domain.Car;
import com.domain.repositories.Cars;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarApplicationService {
	    private final Cars cars;

	    public List<Car> searchAvailableCars(String model) {
	    	return cars.searchAvailable(model, Boolean.TRUE);
	    }
	    
}
