package com.spring.api.hms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.api.hms.entity.RoleDetailsEntity;
import com.spring.api.hms.model.Profile;
import com.spring.api.hms.repository.RoleDetailsRepository;
import com.spring.api.hms.service.Authenticate;

@Service
public class AuthenticateImpl implements Authenticate {

	@Autowired
	RoleDetailsRepository roleDetailsRepository;

	@Override
	public boolean authenticateUser(Profile profile) {

		try {
			RoleDetailsEntity roleDtlsEntity = roleDetailsRepository.findByUserDetails(profile.getEmail(),
					profile.getPassword(), profile.getRole());
			if (roleDtlsEntity != null) {
				return true;
			}
		} catch (RuntimeException e) {
			return false;
		}
		return false;
	}

}
