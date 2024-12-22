package com.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.application.services.RentalApplicationService;
import com.common.dtos.RentalDTO;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalApplicationService rentalService;

    public RentalController(RentalApplicationService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/rent")
    @ResponseStatus(HttpStatus.CREATED)
    public RentalDTO rentCar(@RequestBody RentalDTO rentalDTO) {
        return rentalService.rentCar(rentalDTO);
    }

    @PostMapping("/return/{rentalId}")
    public RentalDTO returnCar(@PathVariable Long rentalId) {
        return rentalService.returnCar(rentalId);
    }

}
