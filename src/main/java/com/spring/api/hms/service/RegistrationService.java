package com.spring.api.hms.service;

import java.util.List;

import com.spring.api.hms.enums.RoleEnum;
import com.spring.api.hms.exception.NoRecordFoundException;
import com.spring.api.hms.model.RegistrationDetails;

public interface RegistrationService {
	public int saveRegistrationDetails(RegistrationDetails registration);

	public List<RegistrationDetails> getAllRegistrationDetailsByRole(RoleEnum role);

	public RegistrationDetails getSpecificRegistraionDetailsByEmail(String email);

	public void updateRegistrationDetails(RegistrationDetails registration);

	public RegistrationDetails getRegistrationDetails(int id) throws NoRecordFoundException;
	
	public String deleteProfile(int id) throws NoRecordFoundException;
	
	public List<RegistrationDetails> getSpecialization(String specializationCriteria) throws NoRecordFoundException;
}
