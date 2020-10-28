package com.spring.api.hms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.api.hms.entity.RoleDetailsEntity;
import com.spring.api.hms.entity.UserDetailsEntity;
import com.spring.api.hms.enums.RoleEnum;
import com.spring.api.hms.model.Registration;
import com.spring.api.hms.repository.RoleDetailsRepository;
import com.spring.api.hms.repository.UserDetailsRepository;
import com.spring.api.hms.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Autowired
	RoleDetailsRepository roleDetailsRepository;

	public void saveRegistrationDetails(Registration registration) {

		UserDetailsEntity userDtls = new UserDetailsEntity(registration.getFirstName(), registration.getLastName(),
				registration.getGender(), registration.getAge(), registration.getAddress(),
				registration.getExistingDiseases(), registration.getQualification(), registration.getSpecialization(),
				registration.getYearOfExp());

		RoleDetailsEntity roleDtls = new RoleDetailsEntity(registration.getEmail(), registration.getPassword(),
				registration.getRole());

		roleDtls.setUserDetails(userDtls);
		userDetailsRepository.save(userDtls);
		roleDetailsRepository.save(roleDtls);
	}

	public List<Registration> getAllRegistrationDetailsByRole(RoleEnum role) {
		List<RoleDetailsEntity> roleDtlsEntityList = roleDetailsRepository.findAllByRole(role);
		List<Registration> registrationList = new ArrayList<>();
		for (RoleDetailsEntity roleDtlsEntity : roleDtlsEntityList) {
			Registration registration = new Registration();
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

	public Registration getSpecificRegistraionDetailsByEmail(String email) {
		RoleDetailsEntity roleDtlsEntity = roleDetailsRepository.findByEmail(email);
		Registration registration = new Registration();
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

	public void updateRegistrationDetails(Registration registration) {

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

}
