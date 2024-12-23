package com.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.common.dtos.CustomerDTO;
import com.domain.Rental;
import com.web.services.CustomerWebService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CustomerWebService customerWebService;

	public CustomerController(CustomerWebService customerWebService) {
		this.customerWebService = customerWebService;
	}

	@Operation(summary = "Search a customer by email", description = "Search available customer by email", responses = {
			@ApiResponse(responseCode = "200", description = "Successful search of customers", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rental.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content),
			@ApiResponse(responseCode = "404", description = "No customer found matching criteria", content = @Content) })
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO findCustomer(@RequestParam(value = "email", required = true) String email) {
		return customerWebService.findCustomer(email);
	}

}
