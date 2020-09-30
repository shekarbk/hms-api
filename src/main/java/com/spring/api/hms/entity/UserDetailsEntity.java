package com.spring.api.hms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.spring.api.hms.enums.GenderEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
//@NoArgsConstructor(force = true)
//@RequiredArgsConstructor
@Table(name = "USER_DETAILS")
public class UserDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String firstName;
	private String lastName;
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private GenderEnum sex;

	private int age;
	private String address;
	private String existingDiseases;
	@OneToOne(mappedBy = "userDetails") // userDetails is a variable name present in RoleDetails class
	private RoleDetailsEntity roleDetails;
	@OneToOne(mappedBy = "userDetails") // userDetails is a variable name present in BookingDetails class
	private BookingDetailsEntity bookingDetails;

	public UserDetailsEntity() {

	}

	public UserDetailsEntity(String firstName, String lastName, GenderEnum sex, int age, String address,
			String existingDiseases) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.age = age;
		this.address = address;
		this.existingDiseases = existingDiseases;
	}
}
