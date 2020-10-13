package com.spring.api.hms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.api.hms.enums.RoleEnum;
import com.spring.api.hms.model.Registration;

@Service
public interface RegistrationService {
	public void saveRegistrationDetails(Registration registration);

	public List<Registration> getAllRegistrationDetailsByRole(RoleEnum role);

	public Registration getSpecificRegistraionDetailsByEmail(String email);

	public void updateRegistrationDetails(Registration registration);
}
