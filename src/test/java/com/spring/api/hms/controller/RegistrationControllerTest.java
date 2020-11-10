package com.spring.api.hms.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.api.hms.constants.HmsConstants;
import com.spring.api.hms.enums.RoleEnum;
import com.spring.api.hms.model.RegistrationDetails;
import com.spring.api.hms.response.Response;
import com.spring.api.hms.service.impl.RegistrationServiceImpl;

@WebMvcTest(controllers = RegistrationController.class)
@ActiveProfiles("test")
public class RegistrationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	RegistrationServiceImpl registrationServiceMock;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	protected void setUp() throws Exception {
	}

	@Test
	public void testSaveRegistrationDetails() throws Exception {
		RegistrationDetails registrationDtls = new RegistrationDetails();
		registrationDtls.setEmail("chandra@gmail.com");
		registrationDtls.setPassword("test123");
		registrationDtls.setLastName("bk");

		Mockito.doNothing().when(registrationServiceMock).saveRegistrationDetails(registrationDtls);
		Response<RegistrationDetails> response = new Response<RegistrationDetails>(HmsConstants.STATUS_SUCCESS,
				HmsConstants.SUCCESS_MESSAGE, null);
		this.mockMvc
				.perform(post("/v1/hms/registration").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(registrationDtls)))
				.andExpect(status().is(200)).andExpect(jsonPath("$.status", is(response.getStatus())));

		Mockito.verify(registrationServiceMock).saveRegistrationDetails(Mockito.any(RegistrationDetails.class));

	}

	@Test
	public void testGetRegistrationDetailsByEmail() throws Exception {
		String email = "chandra@gmail.com";
		RegistrationDetails registrationDtls = new RegistrationDetails();
		registrationDtls.setEmail(email);

		given(registrationServiceMock.getSpecificRegistraionDetailsByEmail(email)).willReturn(registrationDtls);
		Response<RegistrationDetails> response = new Response<RegistrationDetails>(HmsConstants.STATUS_SUCCESS,
				HmsConstants.SUCCESS_MESSAGE, null);

		this.mockMvc.perform(get("/v1/hms/registration/email/{email}", email)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status", is(response.getStatus())));

		Mockito.verify(registrationServiceMock).getSpecificRegistraionDetailsByEmail(email);
	}

	@Test
	public void testGetAllRegistrationDetailsByRole() throws Exception {
		List<RegistrationDetails> registrationList = new ArrayList<>();
		RegistrationDetails registrationDtls = new RegistrationDetails();
		registrationDtls.setRole(RoleEnum.ADMIN);
		registrationList.add(registrationDtls);

		Response<List<RegistrationDetails>> response = new Response<List<RegistrationDetails>>(
				HmsConstants.STATUS_SUCCESS, HmsConstants.SUCCESS_MESSAGE, registrationList);

		given(registrationServiceMock.getAllRegistrationDetailsByRole(RoleEnum.ADMIN)).willReturn(registrationList);
		this.mockMvc.perform(get("/v1/hms/registration/role/{role}", RoleEnum.ADMIN)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status", is(response.getStatus())));

		Mockito.verify(registrationServiceMock).getAllRegistrationDetailsByRole(RoleEnum.ADMIN);
	}

	@Test
	public void testUpdateRegistrationDetails() throws Exception {
		RegistrationDetails registrationDtls = new RegistrationDetails();
		registrationDtls.setEmail("chandra@gmail.com");
		registrationDtls.setPassword("test123");
		registrationDtls.setLastName("bk");

		Mockito.doNothing().when(registrationServiceMock).updateRegistrationDetails(registrationDtls);
		Response<RegistrationDetails> response = new Response<RegistrationDetails>(HmsConstants.STATUS_SUCCESS,
				HmsConstants.UPDATE_MESSAGE, null);

		this.mockMvc
				.perform(put("/v1/hms/registration").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(registrationDtls)))
				.andExpect(status().is(200)).andExpect(jsonPath("$.status", is(response.getStatus())))
				.andExpect(jsonPath("$.message", is(response.getMessage())));

		Mockito.verify(registrationServiceMock).updateRegistrationDetails(Mockito.any(RegistrationDetails.class));
	}

}
