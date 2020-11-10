package com.spring.api.hms.model;

import com.spring.api.hms.enums.RoleEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Profile {
	private String email;
	private String password;
	private RoleEnum role;
}
