package com.spring.api.hms.service;

import com.spring.api.hms.enums.RoleEnum;
import com.spring.api.hms.model.Registration;

public interface ProfileService {
	
	public Registration getProfile(String email, String password, RoleEnum role);
	public String getAllProfileDetails(RoleEnum role);

}
