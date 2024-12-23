package com.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.common.dtos.RentalDTO;
import com.domain.Rental;
import com.web.services.RentalWebService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalWebService rentalWebService;

    public RentalController( RentalWebService rentalWebService) {
        this.rentalWebService = rentalWebService;
    }

    @Operation(
            summary = "Rent a car",
            description = "Rent a car based on the provided data.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful rent of available cars",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Rental.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request parameters",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "No cars found matching criteria",
                            content = @Content)
            }
    )
    @PostMapping("/rent")
    @ResponseStatus(HttpStatus.CREATED)
    public RentalDTO rentCar(@RequestBody RentalDTO rentalDTO) {
        return rentalWebService.rentCar(rentalDTO);
    }

    @PostMapping("/return/{rentalId}")
    public RentalDTO returnCar(@PathVariable Long rentalId) {
        return rentalWebService.returnCar(rentalId);
    }

}
