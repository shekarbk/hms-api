package com.spring.api.hms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.api.hms.constants.HmsConstants;
import com.spring.api.hms.entity.RoleDetailsEntity;
import com.spring.api.hms.entity.UserDetailsEntity;
import com.spring.api.hms.enums.RoleEnum;
import com.spring.api.hms.exception.NoRecordFoundException;
import com.spring.api.hms.model.RegistrationDetails;
import com.spring.api.hms.repository.RoleDetailsRepository;
import com.spring.api.hms.repository.UserDetailsRepository;
import com.spring.api.hms.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Autowired
	RoleDetailsRepository roleDetailsRepository;

	public int saveRegistrationDetails(RegistrationDetails registration) {

		UserDetailsEntity userDtls = new UserDetailsEntity(registration.getFirstName(), registration.getLastName(),
				registration.getGender(), registration.getAge(), registration.getAddress(),
				registration.getExistingDiseases(), registration.getQualification(), registration.getSpecialization(),
				registration.getYearOfExp());

		RoleDetailsEntity roleDtls = new RoleDetailsEntity(registration.getEmail(), registration.getPassword(),
				registration.getRole());

		roleDtls.setUserDetails(userDtls);
		userDtls = userDetailsRepository.save(userDtls);
		roleDetailsRepository.save(roleDtls);
		return userDtls.getUserId();
	}

	public List<RegistrationDetails> getAllRegistrationDetailsByRole(RoleEnum role) {
		List<RoleDetailsEntity> roleDtlsEntityList = roleDetailsRepository.findAllByRole(role);
		List<RegistrationDetails> registrationList = new ArrayList<>();
		for (RoleDetailsEntity roleDtlsEntity : roleDtlsEntityList) {
			RegistrationDetails registration = new RegistrationDetails();
			registration.setEmail(roleDtlsEntity.getEmail());
			registration.setPassword(roleDtlsEntity.getPassword());
			registration.setRole(roleDtlsEntity.getRole());
			registration.setAddress(roleDtlsEntity.getUserDetails().getAddress());
			registration.setFirstName(roleDtlsEntity.getUserDetails().getFirstName());
			registration.setLastName(roleDtlsEntity.getUserDetails().getLastName());
			registration.setGender(roleDtlsEntity.getUserDetails().getSex());
			registration.setExistingDiseases(roleDtlsEntity.getUserDetails().getExistingDiseases());
			registrationList.add(registration);
		}

		return registrationList;
	}

	public RegistrationDetails getSpecificRegistraionDetailsByEmail(String email) {
		RoleDetailsEntity roleDtlsEntity = roleDetailsRepository.findByEmail(email);
		RegistrationDetails registration = new RegistrationDetails();
		registration.setRegistrationId(roleDtlsEntity.getUserDetails().getUserId());
		registration.setEmail(roleDtlsEntity.getEmail());
		registration.setPassword(roleDtlsEntity.getPassword());
		registration.setRole(roleDtlsEntity.getRole());
		registration.setAddress(roleDtlsEntity.getUserDetails().getAddress());
		registration.setFirstName(roleDtlsEntity.getUserDetails().getFirstName());
		registration.setLastName(roleDtlsEntity.getUserDetails().getLastName());
		registration.setGender(roleDtlsEntity.getUserDetails().getSex());
		registration.setExistingDiseases(roleDtlsEntity.getUserDetails().getExistingDiseases());
		return registration;
	}

	public void updateRegistrationDetails(RegistrationDetails registration) {

		Optional<UserDetailsEntity> userDtlsObject = userDetailsRepository.findById(registration.getRegistrationId());
		if (userDtlsObject.isPresent()) {
			UserDetailsEntity userDtls = userDtlsObject.get();

			userDtls.setAddress(registration.getAddress());
			RoleDetailsEntity roleDtlsEntity = userDtls.getRoleDetails();
			roleDtlsEntity.setPassword(registration.getPassword());
//			TODO remove this line if it works
//			userDtls.setRoleDetails(roleDtlsEntity);
			userDetailsRepository.save(userDtls);
		}

	}

	@Override
	public RegistrationDetails getRegistrationDetails(int id) throws NoRecordFoundException {
		Optional<UserDetailsEntity> userDtlsObject = userDetailsRepository.findById(id);
		RegistrationDetails registrationDetails = new RegistrationDetails();

		if (userDtlsObject.isPresent()) {
			UserDetailsEntity userDtlsEntity = userDtlsObject.get();
			registrationDetails.setRegistrationId(userDtlsEntity.getUserId());
			registrationDetails.setFirstName(userDtlsEntity.getFirstName());
			registrationDetails.setLastName(userDtlsEntity.getLastName());
			registrationDetails.setAddress(userDtlsEntity.getAddress());
			registrationDetails.setGender(userDtlsEntity.getSex());
			registrationDetails.setAge(userDtlsEntity.getAge());
			registrationDetails.setAddress(userDtlsEntity.getAddress());
			registrationDetails.setExistingDiseases(userDtlsEntity.getExistingDiseases());
			registrationDetails.setQualification(userDtlsEntity.getQualification());
			registrationDetails.setSpecialization(userDtlsEntity.getSpecialization());
			registrationDetails.setYearOfExp(userDtlsEntity.getYearOfExp());
			registrationDetails.setPassword(userDtlsEntity.getRoleDetails().getPassword());
			registrationDetails.setEmail(userDtlsEntity.getRoleDetails().getEmail());
			registrationDetails.setRole(userDtlsEntity.getRoleDetails().getRole());
		} else {
			throw new NoRecordFoundException("registration id not found !!!");
		}
		return registrationDetails;
	}

	@Override
	public String deleteProfile(int id) throws NoRecordFoundException {
		try {
			userDetailsRepository.deleteById(id);
		} catch (Exception e) {
			throw new NoRecordFoundException("No record found !!!");
		}
		return HmsConstants.DELETE_MESSAGE;
	}

}
