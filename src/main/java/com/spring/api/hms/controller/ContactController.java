package com.spring.api.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.hms.constants.HmsConstants;
import com.spring.api.hms.exception.NoRecordFoundException;
import com.spring.api.hms.model.ContactDetails;
import com.spring.api.hms.response.Response;
import com.spring.api.hms.service.ContactDetailsService;

@RestController
@RequestMapping("/v1/hms/contact")
public class ContactController {

	@Autowired
	ContactDetailsService contactService;

	@PostMapping
	public Response<ContactDetails> addNewContact(@RequestBody ContactDetails contactDetails) {
		contactService.addNewContact(contactDetails);
		return new Response<ContactDetails>(HmsConstants.STATUS_SUCCESS, HmsConstants.SUCCESS_MESSAGE, null);
	}

	@GetMapping
	public Response<List<ContactDetails>> getAllContacts() {
		List<ContactDetails> contactsVoList = contactService.getAllContacts();
		if (contactsVoList.isEmpty()) {
			return new Response<List<ContactDetails>>(HmsConstants.STATUS_FAILED, HmsConstants.NO_RECORDS_FOUND, null);
		}
		return new Response<List<ContactDetails>>(HmsConstants.STATUS_SUCCESS, null, contactsVoList);
	}

	@PutMapping
	public Response<ContactDetails> updateContact(@RequestBody ContactDetails contactDetails) {
		try {
			contactService.updateContact(contactDetails);
		} catch (NoRecordFoundException e) {
			return new Response<ContactDetails>(HmsConstants.STATUS_FAILED, e.getMessage(), null);
		}
		return new Response<ContactDetails>(HmsConstants.STATUS_SUCCESS, HmsConstants.UPDATE_MESSAGE, null);
	}
}
