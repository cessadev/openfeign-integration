package com.cessadev.api_integration_and_tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiIntegrationAndTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiIntegrationAndTestsApplication.class, args);
	}

}
