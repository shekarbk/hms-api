package com.spring.api.hms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name="CONTACT_DETAILS") 
public class ContactDetailsEntity {
	@Id
	@GeneratedValue

	private int contactId;
	private String name;
	private int phoneNumber;
	private String email;
	private String address;
	private boolean isBranch;
	private boolean isSubBranch;
	private boolean isHeadOffice;
	
}
