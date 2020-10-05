package com.spring.api.hms.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import com.spring.api.hms.enums.RoleEnum;
import com.spring.api.hms.model.Registration;
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
		Registration registrationDtls = new Registration();
		registrationDtls.setEmail("chandra@gmail.com");
		registrationDtls.setPassword("test123");
		registrationDtls.setLastName("bk");

		Mockito.doNothing().when(registrationServiceMock).saveRegistrationDetails(registrationDtls);

		this.mockMvc
				.perform(post("/v1/hms/registration").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(registrationDtls)))
				.andExpect(status().is(200)).andExpect(content().string("details saved successfully !!!"));

		Mockito.verify(registrationServiceMock).saveRegistrationDetails(Mockito.any(Registration.class));

	}

	@Test
	public void testGetRegistrationDetailsByEmail() throws Exception {
		String email = "chandra@gmail.com";
		Registration registrationDtls = new Registration();
		registrationDtls.setEmail(email);

		given(registrationServiceMock.getSpecificRegistraionDetailsByEmail(email)).willReturn(registrationDtls);

		this.mockMvc.perform(get("/v1/hms/registration/email/{email}", email)).andExpect(status().isOk())
				.andExpect(jsonPath("$.email", is(registrationDtls.getEmail())));

		Mockito.verify(registrationServiceMock).getSpecificRegistraionDetailsByEmail(email);
	}

	@Test
	public void testGetAllRegistrationDetailsByRole() throws Exception {
		List<Registration> registrationList = new ArrayList<>();
		Registration registrationDtls = new Registration();
		registrationDtls.setRole(RoleEnum.ADMIN);

		given(registrationServiceMock.getAllRegistrationDetailsByRole(RoleEnum.ADMIN)).willReturn(registrationList);
		this.mockMvc.perform(get("/v1/hms/registration/role/{role}", RoleEnum.ADMIN)).andExpect(status().isOk());
//		.andExpect(jsonPath("$.role", is(registrationDtls.getRole())));
		
		Mockito.verify(registrationServiceMock).getAllRegistrationDetailsByRole(RoleEnum.ADMIN);
	}

	@Test
	public void testUpdateRegistrationDetails() throws Exception {
		Registration registrationDtls = new Registration();
		registrationDtls.setEmail("chandra@gmail.com");
		registrationDtls.setPassword("test123");
		registrationDtls.setLastName("bk");
		
		Mockito.doNothing().when(registrationServiceMock).updateRegistrationDetails(registrationDtls);

		this.mockMvc
		.perform(put("/v1/hms/registration").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(registrationDtls)))
		.andExpect(status().is(200)).andExpect(content().string("details updated successfully !!!"));

		Mockito.verify(registrationServiceMock).updateRegistrationDetails(Mockito.any(Registration.class));

	}

}
