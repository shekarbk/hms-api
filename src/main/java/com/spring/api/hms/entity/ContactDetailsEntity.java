package com.spring.api.hms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@Table(name = "CONTACT_DETAILS")
public class ContactDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contactId;
	private String name;
	private String countryCode;
	private String phoneNumber;
	private String email;
	private String address;
	private String branch;
	private String subBranch;
	private String headOffice;

	public ContactDetailsEntity(String name, String countryCode, String phoneNumber, String email, String address, String branch,
			String subBranch, String headOffice) {
		super();
		this.name = name;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.branch = branch;
		this.subBranch = subBranch;
		this.headOffice = headOffice;
	}

}
