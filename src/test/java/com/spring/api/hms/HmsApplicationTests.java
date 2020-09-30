package com.spring.api.hms;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.api.hms.controller.RegistrationController;

@SpringBootTest
class HmsApplicationTests {
	
	@Autowired
	private RegistrationController registrationController;
	
	@Test
	void contextLoads() {
		assertNotNull(registrationController);
	}

}
