package com.spring.api.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.hms.constants.HmsConstants;
import com.spring.api.hms.enums.RoleEnum;
import com.spring.api.hms.exception.NoRecordFoundException;
import com.spring.api.hms.model.RegistrationDetails;
import com.spring.api.hms.response.Response;
import com.spring.api.hms.service.RegistrationService;

@RestController
@RequestMapping("/v1/hms/registration")
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@PostMapping
	public Response<RegistrationDetails> addNewRegistration(@RequestBody RegistrationDetails registration) {
		registrationService.saveRegistrationDetails(registration);
		return new Response<RegistrationDetails>(HmsConstants.STATUS_SUCCESS, HmsConstants.SUCCESS_MESSAGE, null);
	}

	@GetMapping("/email/{email}")
	public Response<RegistrationDetails> getRegistrationDetailsByEmail(@PathVariable("email") String email) {
		RegistrationDetails registrationDtls = registrationService.getSpecificRegistraionDetailsByEmail(email);
		return new Response<RegistrationDetails>(HmsConstants.STATUS_SUCCESS, HmsConstants.SUCCESS_FETCH_MESSAGE,
				registrationDtls);

	}

	@GetMapping("/role/{role}")
	public Response<List<RegistrationDetails>> getAllRegistrationDetailsByRole(@PathVariable RoleEnum role) {
		List<RegistrationDetails> registrationList = registrationService.getAllRegistrationDetailsByRole(role);
		if (registrationList.isEmpty()) {
			return new Response<List<RegistrationDetails>>(HmsConstants.STATUS_FAILED, HmsConstants.NO_RECORDS_FOUND,
					registrationList);
		}
		return new Response<List<RegistrationDetails>>(HmsConstants.STATUS_SUCCESS, HmsConstants.SUCCESS_MESSAGE,
				registrationList);
	}

	@PutMapping
	public Response<RegistrationDetails> UpdateRegistrationDetails(@RequestBody RegistrationDetails registration) {
		registrationService.updateRegistrationDetails(registration);
		return new Response<RegistrationDetails>(HmsConstants.STATUS_SUCCESS, HmsConstants.UPDATE_MESSAGE, null);
	}

	@GetMapping("/{id}")
	public Response<RegistrationDetails> getRegistrationDetails(@PathVariable("id") int id) {
		RegistrationDetails registrationDtls = null;
		try {
			registrationDtls = registrationService.getRegistrationDetails(id);
		} catch (NoRecordFoundException e) {
			return new Response<RegistrationDetails>(HmsConstants.STATUS_FAILED, e.getMessage(), null);
		}
		return new Response<RegistrationDetails>(HmsConstants.STATUS_SUCCESS, HmsConstants.SUCCESS_FETCH_MESSAGE,
				registrationDtls);
	}
	
	@DeleteMapping("/{id}")
	public Response<RegistrationDetails> deleteProfile(@PathVariable("id") int id) {
		try {
			registrationService.deleteProfile(id);
		} catch (NoRecordFoundException e) {
			return new Response<RegistrationDetails>(HmsConstants.STATUS_FAILED, e.getMessage(), null);
		}
		return new Response<RegistrationDetails>(HmsConstants.STATUS_SUCCESS, HmsConstants.DELETE_MESSAGE, null);
	}
}
