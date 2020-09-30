package com.spring.api.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.hms.enums.RoleEnum;
import com.spring.api.hms.model.Registration;
import com.spring.api.hms.service.impl.RegistrationServiceImpl;

@RestController
@RequestMapping("/v1/hms")
public class RegistrationController {

	@Autowired
	RegistrationServiceImpl registrationService;

	@PostMapping("/registration")
	public String saveRegistrationDetails(@RequestBody Registration registration) {
		registrationService.saveRegistrationDetails(registration);
		return "user details saved successfully !!!";
	}

	@GetMapping("/registration/email/{email}")
	public Registration getRegistrationDetailsByEmail(@PathVariable("email") String email) {
		Registration registrationDtls = registrationService.getSpecificRegistraionDetailsByEmail(email);
		return registrationDtls;

	}

	@GetMapping("/registration/role/{role}")
	public List<Registration> getAllRegistrationDetailsByRole(@PathVariable RoleEnum role) {
		List<Registration> registrationList = registrationService.getAllRegistrationDetailsByRole(role);
		return registrationList;
	}
	
	@PutMapping("/registration")
	public String UpdateRegistrationDetails(@RequestBody Registration registration) {
		registrationService.updateRegistrationDetails(registration);
		return "user details updated successfully !!!";
		
	}
}
