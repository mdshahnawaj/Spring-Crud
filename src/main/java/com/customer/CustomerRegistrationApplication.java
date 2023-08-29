package com.customer;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * This is main class where spring boot project is start
 */
@SpringBootApplication
public class CustomerRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerRegistrationApplication.class, args);
	}

	/**
	 * Creates and configures a ModelMapper instance.
	 * @return A configured ModelMapper instance that can be used for object mapping.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}