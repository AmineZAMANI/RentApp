package com.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.common.dtos.CarDTO;
import com.domain.Rental;
import com.web.services.CarWebService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarWebService carWebService;

    public CarController( CarWebService carWebService) {
        this.carWebService = carWebService;
    }

    @Operation(
            summary = "Search a car by model",
            description = "Search available cars by model",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful search of available cars",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Rental.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request parameters",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "No cars found matching criteria",
                            content = @Content)
            }
    )
    @GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
    public List<CarDTO> findCar(@RequestParam(value = "model", required = true) String model) {
        return carWebService.findCar(model);
    }


}
