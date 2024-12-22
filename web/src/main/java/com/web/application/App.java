package com.web.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.application", "com.web", "com.infra", "com.common" })
@EntityScan(basePackages = { "com.infra"})
@EnableJpaRepositories(basePackages = {"com.infra"})
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
