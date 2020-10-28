package com.spring.api.hms.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ContactDetails {
	
	private int contactId;
	private String name;
	private String countryCode;
	private String phoneNumber;
	private String email;
	private String address;
	private String branch;
	private String subBranch;
	private String headOffice;

}
