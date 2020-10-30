package com.spring.api.hms.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
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
import com.spring.api.hms.exception.NoRecordFoundException;
import com.spring.api.hms.model.BookingDetails;
import com.spring.api.hms.model.ContactDetails;
import com.spring.api.hms.response.Response;
import com.spring.api.hms.service.ContactDetailsService;

@WebMvcTest(controllers = ContactController.class)
@ActiveProfiles("test")
public class ContactControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ContactDetailsService contactDetailsServiceMock;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	protected void setUp() throws Exception {
	}

	@Test
	public void testAddNewContact() throws JsonProcessingException, Exception {
		Mockito.doNothing().when(contactDetailsServiceMock).addNewContact(Mockito.any(ContactDetails.class));
		Response<BookingDetails> response = new Response<BookingDetails>(HmsConstants.STATUS_SUCCESS, null, null);
		ContactDetails contactDetails = new ContactDetails();

		this.mockMvc
				.perform(post("/v1/hms/contact").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(contactDetails)))
				.andExpect(status().is(200)).andExpect(jsonPath("$.status", is(response.getStatus())));
	}

	@Test
	public void testGetAllContacts() throws Exception {
		List<ContactDetails> contactsVoList = new ArrayList<ContactDetails>();
		ContactDetails contactDetails = new ContactDetails();
		contactsVoList.add(contactDetails);
		Response<BookingDetails> response = new Response<BookingDetails>(HmsConstants.STATUS_SUCCESS, null, null);
		Mockito.when(contactDetailsServiceMock.getAllContacts()).thenReturn(contactsVoList);

		this.mockMvc.perform(get("/v1/hms/contact")).andExpect(jsonPath("$.status", is(response.getStatus())));
	}

	@Test
	public void testUpdateContact() throws JsonProcessingException, Exception {
		ContactDetails contactDetails = new ContactDetails();
		Mockito.doNothing().when(contactDetailsServiceMock).updateContact(Mockito.any(ContactDetails.class));
		Response<BookingDetails> response = new Response<BookingDetails>(HmsConstants.STATUS_SUCCESS, null, null);

		this.mockMvc
				.perform(put("/v1/hms/contact").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(contactDetails)))
				.andExpect(jsonPath("$.status", is(response.getStatus())));
	}

	@Test
	public void testUpdateContactWithExpectedException() throws JsonProcessingException, Exception {
		//Throw the exception
		Mockito.doThrow(NoRecordFoundException.class).when(contactDetailsServiceMock)
				.updateContact(Mockito.any(ContactDetails.class));

		ContactDetails contactDetails = new ContactDetails();
		Response<BookingDetails> response = new Response<BookingDetails>(HmsConstants.STATUS_FAILED, null, null);
		this.mockMvc
				.perform(put("/v1/hms/contact").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(contactDetails)))
				.andExpect(jsonPath("$.status", is(response.getStatus())));

	}
}
