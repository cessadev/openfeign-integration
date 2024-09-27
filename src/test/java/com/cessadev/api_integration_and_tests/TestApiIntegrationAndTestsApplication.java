package com.cessadev.api_integration_and_tests;

import org.springframework.boot.SpringApplication;

public class TestApiIntegrationAndTestsApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApiIntegrationAndTestsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
