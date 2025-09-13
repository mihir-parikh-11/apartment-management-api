package com.apartment.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApartmentManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApartmentManagementApiApplication.class, args);
	}
}
