package com.spring.api.hms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.spring.api.hms.entity.ContactDetailsEntity;
import com.spring.api.hms.exception.NoRecordFoundException;
import com.spring.api.hms.model.ContactDetails;
import com.spring.api.hms.repository.ContactDetailsRepository;

@RunWith(MockitoJUnitRunner.class)
public class ContactDetailsServiceImplTest {

	@Mock
	ContactDetailsRepository contactDetailsRepository;

	@InjectMocks
	ContactDetailsServiceImpl contactDetailsServiceImpl;

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testUpdateContact() throws NoRecordFoundException {
		ContactDetailsEntity contactEntity = new ContactDetailsEntity();
		ContactDetails contactDetails = new ContactDetails();
		Mockito.when(contactDetailsRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(contactEntity));
		Mockito.when(contactDetailsRepository.save(Mockito.any(ContactDetailsEntity.class)))
				.thenReturn(Mockito.any(ContactDetailsEntity.class));
		contactDetailsServiceImpl.updateContact(contactDetails);
		Mockito.verify(contactDetailsRepository).findById(Mockito.anyInt());
	}

	@Test
	public void testUpdateContactWithExpectedException() {
		ContactDetails contactDetails = new ContactDetails();
		Exception exception = Assertions.assertThrows(NoRecordFoundException.class, () -> {
			Mockito.when(contactDetailsRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
			contactDetailsServiceImpl.updateContact(contactDetails);
		});
		
		Assertions.assertTrue(exception.getClass().equals(NoRecordFoundException.class));
		Mockito.verify(contactDetailsRepository).findById(Mockito.anyInt());
	}

	@Test
	public void testGetAllContacts() {
		List<ContactDetailsEntity> contactEntityList = new ArrayList<ContactDetailsEntity>();
		ContactDetailsEntity contactDetailsEntity = new ContactDetailsEntity();
		contactEntityList.add(contactDetailsEntity);
		Mockito.when(contactDetailsRepository.findAll()).thenReturn(contactEntityList);
		contactDetailsServiceImpl.getAllContacts();
		Mockito.verify(contactDetailsRepository).findAll();
	}

	@Test
	public void testAddNewContact() {
		ContactDetails contactDetails = new ContactDetails();
		Mockito.when(contactDetailsRepository.save(Mockito.any(ContactDetailsEntity.class)))
				.thenReturn(Mockito.any(ContactDetailsEntity.class));
		contactDetailsServiceImpl.addNewContact(contactDetails);
		Mockito.verify(contactDetailsRepository).save(Mockito.any(ContactDetailsEntity.class));
	}

}
