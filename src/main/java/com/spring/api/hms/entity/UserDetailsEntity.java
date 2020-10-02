package com.spring.api.hms.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.spring.api.hms.enums.GenderEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
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
	private String qualification;
	private String specialization;
	private String yearOfExp;

	@OneToOne(mappedBy = "userDetails") // userDetails is a variable name present in RoleDetailsEntity class
	private RoleDetailsEntity roleDetails;
	
	@OneToMany(mappedBy = "patientId", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //patientId is the variable name present in BookingDetailsEntity class
	private Set<BookingDetailsEntity> patientId;
	
	@OneToMany(mappedBy = "doctorId", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //doctorId is the variable name present in BookingDetailsEntity class
	private Set<BookingDetailsEntity> doctorId;
	

	public UserDetailsEntity(String firstName, String lastName, GenderEnum sex, int age, String address,
			String existingDiseases, String qualification, String specialization, String yearOfExp) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.age = age;
		this.address = address;
		this.existingDiseases = existingDiseases;
		this.qualification = qualification;
		this.specialization = specialization;
		this.yearOfExp = yearOfExp;
	}
}
