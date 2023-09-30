package com.optimed.appointmentmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.optimed.appointmentmicroservice.repository")
@EntityScan(basePackages = "com.optimed.appointmentmicroservice.model")
public class AppointmentMicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppointmentMicroserviceApplication.class, args);
	}

}
