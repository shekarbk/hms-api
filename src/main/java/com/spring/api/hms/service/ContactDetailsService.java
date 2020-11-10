package com.spring.api.hms.service;

import java.util.List;

import com.spring.api.hms.exception.NoRecordFoundException;
import com.spring.api.hms.model.ContactDetails;

public interface ContactDetailsService {
	void updateContact(ContactDetails contactDetails) throws NoRecordFoundException;

	List<ContactDetails> getAllContacts();

	void addNewContact(ContactDetails contactDetails);
}
