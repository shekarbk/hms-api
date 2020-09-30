package com.spring.api.hms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.spring.api.hms.enums.RoleEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
//@NoArgsConstructor(force = true)
//@RequiredArgsConstructor
@Table(name = "ROLE_DETAILS")
public class RoleDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private RoleEnum role;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", nullable = false)
	private UserDetailsEntity userDetails;

	public RoleDetailsEntity() {

	}

	public RoleDetailsEntity(String email, String password, RoleEnum role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}

}
