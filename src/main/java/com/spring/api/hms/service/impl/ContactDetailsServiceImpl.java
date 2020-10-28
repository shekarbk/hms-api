package com.spring.api.hms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.api.hms.entity.ContactDetailsEntity;
import com.spring.api.hms.exception.NoRecordFoundException;
import com.spring.api.hms.model.ContactDetails;
import com.spring.api.hms.repository.ContactDetailsRepository;
import com.spring.api.hms.service.ContactDetailsService;

@Service
public class ContactDetailsServiceImpl implements ContactDetailsService {

	@Autowired
	ContactDetailsRepository contactDetailsRepository;

	@Override
	public void updateContact(ContactDetails contactDetails) throws NoRecordFoundException {
		Optional<ContactDetailsEntity> contactEntityOptional = contactDetailsRepository
				.findById(contactDetails.getContactId());
		if (!contactEntityOptional.isPresent()) {
			throw new NoRecordFoundException("either patient id or doctor id not found !!!");
		} else {
			ContactDetailsEntity contactEntity = contactEntityOptional.get();
			contactEntity.setContactId(contactDetails.getContactId());
			contactEntity.setName(contactDetails.getName());
			contactEntity.setPhoneNumber(contactDetails.getPhoneNumber());
			contactEntity.setAddress(contactDetails.getAddress());
			contactEntity.setCountryCode(contactDetails.getCountryCode());
			contactEntity.setBranch(contactDetails.getBranch());
			contactEntity.setEmail(contactDetails.getEmail());
			contactEntity.setSubBranch(contactDetails.getSubBranch());
			contactEntity.setHeadOffice(contactDetails.getHeadOffice());
			contactDetailsRepository.save(contactEntity);
		}
	}

	@Override
	public List<ContactDetails> getAllContacts() {
		List<ContactDetailsEntity> contactEntityList = contactDetailsRepository.findAll();
		List<ContactDetails> contactsVoList = new ArrayList<ContactDetails>();
		for (ContactDetailsEntity contactDetailsEntity : contactEntityList) {
			ContactDetails contactVo = new ContactDetails();
			contactVo.setContactId(contactDetailsEntity.getContactId());
			contactVo.setName(contactDetailsEntity.getName());
			contactVo.setPhoneNumber(contactDetailsEntity.getPhoneNumber());
			contactVo.setAddress(contactDetailsEntity.getAddress());
			contactVo.setCountryCode(contactDetailsEntity.getCountryCode());
			contactVo.setBranch(contactDetailsEntity.getBranch());
			contactVo.setEmail(contactDetailsEntity.getEmail());
			contactVo.setSubBranch(contactDetailsEntity.getSubBranch());
			contactVo.setHeadOffice(contactDetailsEntity.getHeadOffice());
			contactsVoList.add(contactVo);
		}
		return contactsVoList;
	}

	@Override
	public void addNewContact(ContactDetails contactDetails) {
		ContactDetailsEntity contactEntity = new ContactDetailsEntity(contactDetails.getName(),
				contactDetails.getCountryCode(), contactDetails.getPhoneNumber(), contactDetails.getEmail(),
				contactDetails.getAddress(), contactDetails.getBranch(), contactDetails.getSubBranch(),
				contactDetails.getHeadOffice());
		contactDetailsRepository.save(contactEntity);

	}

}
