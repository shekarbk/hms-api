package com.spring.api.hms.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ContactDetails {
	
	private int contactId;
	private String name;
	private int phoneNumber;
	private String email;
	private String address;
	private boolean isBranch;
	private boolean isSubBranch;
	private boolean isHeadOffice;

}
