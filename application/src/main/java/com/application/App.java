package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.infra.services", "com.domain", "com.application"})
@EntityScan(basePackages = {"com.domain", "com.infra.services.*"})
@EnableJpaRepositories(basePackages = "com.infra.repositories")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
