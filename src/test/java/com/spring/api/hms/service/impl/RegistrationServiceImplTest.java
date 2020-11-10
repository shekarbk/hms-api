package com.spring.api.hms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.spring.api.hms.entity.RoleDetailsEntity;
import com.spring.api.hms.entity.UserDetailsEntity;
import com.spring.api.hms.enums.GenderEnum;
import com.spring.api.hms.enums.RoleEnum;
import com.spring.api.hms.model.RegistrationDetails;
import com.spring.api.hms.repository.RoleDetailsRepository;
import com.spring.api.hms.repository.UserDetailsRepository;

@RunWith(MockitoJUnitRunner.class)

public class RegistrationServiceImplTest {
	@Mock
	UserDetailsRepository userDetailsRepository;

	@Mock
	RoleDetailsRepository roleDetailsRepository;

	@InjectMocks
	RegistrationServiceImpl registrationService;

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveRegistrationDetails() throws Exception {
		RegistrationDetails registration = new RegistrationDetails();

		UserDetailsEntity userDtlsObj = new UserDetailsEntity();
		RoleDetailsEntity roleDtlsObj = new RoleDetailsEntity();

		Mockito.when(userDetailsRepository.save(Mockito.any(UserDetailsEntity.class))).thenReturn(userDtlsObj);
		Mockito.when(roleDetailsRepository.save(Mockito.any(RoleDetailsEntity.class))).thenReturn(roleDtlsObj);

		registrationService.saveRegistrationDetails(registration);

		Mockito.verify(userDetailsRepository).save(Mockito.any(UserDetailsEntity.class));
		Mockito.verify(roleDetailsRepository).save(Mockito.any(RoleDetailsEntity.class));

	}

	@Test
	public void testGetAllRegistrationDetailsByRole() throws Exception {
		List<RoleDetailsEntity> roleDtlsEntityList = new ArrayList<>();
		UserDetailsEntity userDtlsObj = new UserDetailsEntity();
		userDtlsObj.setAddress("address");
		userDtlsObj.setFirstName("firstName");
		userDtlsObj.setLastName("lastName");
		userDtlsObj.setSex(GenderEnum.MALE);
		userDtlsObj.setExistingDiseases("existingDiseases");
		
		RoleDetailsEntity roleDetailsEntity = new RoleDetailsEntity();
		roleDetailsEntity.setUserDetails(userDtlsObj);
		roleDtlsEntityList.add(roleDetailsEntity);
		
		Mockito.when(roleDetailsRepository.findAllByRole(RoleEnum.ADMIN)).thenReturn(roleDtlsEntityList);
		registrationService.getAllRegistrationDetailsByRole(RoleEnum.ADMIN);

		Mockito.verify(roleDetailsRepository).findAllByRole(RoleEnum.ADMIN);
	}

	@Test
	public void testGetSpecificRegistraionDetailsByEmail() throws Exception {
		RoleDetailsEntity roleDtlsEntity = new RoleDetailsEntity();
		UserDetailsEntity userDtlsObj = new UserDetailsEntity();
		roleDtlsEntity.setUserDetails(userDtlsObj);

		Mockito.when(roleDetailsRepository.findByEmail(Mockito.any(String.class))).thenReturn(roleDtlsEntity);

		registrationService.getSpecificRegistraionDetailsByEmail("chandra@gmail.com");

		Mockito.verify(roleDetailsRepository).findByEmail("chandra@gmail.com");
	}

	@Test
	public void testUpdateRegistrationDetails() throws Exception {
		RoleDetailsEntity roleDtlsEntity = new RoleDetailsEntity();
		UserDetailsEntity userDtlsObj = new UserDetailsEntity();
		userDtlsObj.setRoleDetails(roleDtlsEntity);

		RegistrationDetails registration = new RegistrationDetails();

		Mockito.when(userDetailsRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(userDtlsObj));

		registrationService.updateRegistrationDetails(registration);

		Mockito.verify(userDetailsRepository).findById(Mockito.any(Integer.class));

	}

}
