package com.spring.api.hms.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.api.hms.constants.HmsConstants;
import com.spring.api.hms.model.BookingDetails;
import com.spring.api.hms.model.Profile;
import com.spring.api.hms.response.Response;
import com.spring.api.hms.service.Authenticate;

@WebMvcTest(controllers = AutenticateController.class)
@ActiveProfiles("test")
public class AutenticateControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	Authenticate authenticateServiceMock;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeEach
	protected void setUp() throws Exception {
	}

	@Test
	public void testAuthenticateUserWithExceptedSuccess() throws JsonProcessingException, Exception {
		Mockito.when(authenticateServiceMock.authenticateUser(Mockito.any(Profile.class))).thenReturn(true);
		Profile profile = new Profile();
		Response<BookingDetails> response = new Response<BookingDetails>(HmsConstants.STATUS_SUCCESS, null, null);
		
		this.mockMvc
		.perform(post("/v1/hms/authenticate").header("Content-Type", MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(profile)))
		.andExpect(status().is(200)).andExpect(jsonPath("$.status", is(response.getStatus())));
		
	}
	
	@Test
	public void testAuthenticateUserWithExpectedFailure() throws JsonProcessingException, Exception {
		Mockito.when(authenticateServiceMock.authenticateUser(Mockito.any(Profile.class))).thenReturn(false);
		Profile profile = new Profile();
		Response<BookingDetails> response = new Response<BookingDetails>(HmsConstants.STATUS_FAILED, null, null);
		
		this.mockMvc
		.perform(post("/v1/hms/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(profile)))
		.andExpect(status().is(200)).andExpect(jsonPath("$.status", is(response.getStatus())));

	}

}
