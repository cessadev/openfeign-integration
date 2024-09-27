package com.cessadev.api_integration_and_tests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ApiIntegrationAndTestsApplicationTests {

	@Test
	void contextLoads() {
	}

}
