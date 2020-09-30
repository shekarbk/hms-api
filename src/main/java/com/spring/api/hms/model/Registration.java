package com.spring.api.hms.model;

import com.spring.api.hms.enums.GenderEnum;
import com.spring.api.hms.enums.RoleEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Registration {
	private int registrationId;
	private String firstName;
	private String lastName;
	private GenderEnum gender;
	private int age;
	private String address;
	private String existingDiseases;
	private String email;
	private String password;
	private RoleEnum role;
}
