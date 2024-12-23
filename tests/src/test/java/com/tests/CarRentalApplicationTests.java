package com.tests;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.domain.Car;
import com.domain.Customer;
import com.domain.Rental;
import com.domain.RentalPeriod;
import com.domain.repositories.Cars;
import com.domain.repositories.Customers;
import com.domain.repositories.Rentals;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = com.web.application.App.class)
class CarRentalApplicationTests {
	

	@Autowired
	private Cars cars;

	@Autowired
	private Customers customers;

	@Autowired
	private Rentals rentals;

	@Test
	void contextLoads() {
	}

	@Test
	public void testSearchAvailableCars() {
		List<Car> result = cars.searchAvailable("Mercedes GLC", true);

		assertThat(result).isNotEmpty();
		assertThat(result.get(0).getModel()).isEqualTo("Mercedes GLC");
	}

	@Test
	public void testSearchAvailableCars_WhenNoCarsAvailable() {
		List<Car> result = cars.searchAvailable( "Mercedes GLC", false);
		assertThat(result).isEmpty();
	}

	@Test
	public void testSearchCustomerByLicenseNumberOrEmail_Found() {
		Optional<Customer> resultByLicense = customers.searchByEmail("zmi.amn@gmail.com");
		assertThat(resultByLicense).isPresent();
		assertThat(resultByLicense.get().getEmail()).isEqualTo("zmi.amn@gmail.com");

		Optional<Customer> resultByEmail = customers.searchByEmail("zmi.amn@gmail.com");
		assertThat(resultByEmail).isPresent();
		assertThat(resultByEmail.get().getName()).isEqualTo("Amine ZAMANI");
	}

	@Test
	public void testSearchCustomerByLicenseNumberOrEmail_NotFound() {
		Optional<Customer> result = customers.searchByEmail("zmi.amn@gmail.com");
		assertThat(result).isEmpty();
	}

	@Test
	public void testRentAndReturnCar() {
		// Step 1: Fetch existing customer
		Optional<Customer> customerOpt = customers.searchByEmail("zmi.amn@gmail.com");
		assertThat(customerOpt).isPresent();
		Customer customer = customerOpt.get();

		// Step 2: Search for available cars
		LocalDate startDate = LocalDate.of(2025, 1, 10);
		LocalDate endDate = LocalDate.of(2025, 1, 12);

		List<Car> availableCars = cars.searchAvailable("Mercedes GLC", true);
		assertThat(availableCars).isNotEmpty();

		Car carToRent = availableCars.get(0);

		Rental mRental = Rental.builder().car(carToRent).customer(customer)
				.rentalPeriod(RentalPeriod.builder().startDate(startDate).endDate(endDate).build()).build();
		// Step 3: Rent the car customer.getId(), carToRent.getId(), startDate, endDate
		Rental rental = rentals.rent(mRental);

		assertThat(rental).isNotNull();
		assertThat(rental.getCar().isAvailable());

		// Step 4: Return the car
		Rental returnedRental = rentals.returnCar(rental);

		assertThat(returnedRental).isNotNull();
		assertThat(returnedRental.getCar().isAvailable());
	}

}