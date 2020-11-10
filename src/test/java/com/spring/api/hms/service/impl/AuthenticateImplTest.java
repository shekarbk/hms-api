package com.spring.api.hms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.spring.api.hms.entity.RoleDetailsEntity;
import com.spring.api.hms.enums.RoleEnum;
import com.spring.api.hms.model.Profile;
import com.spring.api.hms.repository.RoleDetailsRepository;

public class AuthenticateImplTest {

	@Mock
	RoleDetailsRepository roleDetailsRepository;

	@InjectMocks
	AuthenticateImpl authenticateImpl;

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAuthenticateUser() {
		RoleDetailsEntity roleDtlsEntity = new RoleDetailsEntity();
		Profile profile = new Profile();
		profile.setRole(RoleEnum.ADMIN);
		profile.setEmail("");
		profile.setPassword("");

		Mockito.when(roleDetailsRepository.findByUserDetails(Mockito.anyString(), Mockito.anyString(),
				Mockito.any(RoleEnum.class))).thenReturn(roleDtlsEntity);
		boolean result = authenticateImpl.authenticateUser(profile);
		assertEquals(true, result);
	}

	@Test
	public void testAuthenticateUserInvalidUser() {
		RoleDetailsEntity roleDtlsEntity = new RoleDetailsEntity();
		Profile profile = new Profile();

		Mockito.when(roleDetailsRepository.findByUserDetails(Mockito.anyString(), Mockito.anyString(),
				Mockito.any(RoleEnum.class))).thenReturn(roleDtlsEntity);
		boolean result = authenticateImpl.authenticateUser(profile);
		assertEquals(false, result);
	}

	@Test
	public void testAuthenticationWithException() {
		Profile profile = new Profile();
		profile.setRole(RoleEnum.ADMIN);
		profile.setEmail("");
		profile.setPassword("");

		Mockito.doThrow(RuntimeException.class).when(roleDetailsRepository).findByUserDetails(Mockito.anyString(),
				Mockito.anyString(), Mockito.any(RoleEnum.class));
		boolean result = authenticateImpl.authenticateUser(profile);

		Mockito.verify(roleDetailsRepository).findByUserDetails(Mockito.anyString(), Mockito.anyString(),
				Mockito.any(RoleEnum.class));

		assertEquals(false, result);
	}

}
