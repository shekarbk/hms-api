package com.spring.api.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.hms.constants.HmsConstants;
import com.spring.api.hms.model.Profile;
import com.spring.api.hms.response.Response;
import com.spring.api.hms.service.Authenticate;

@RestController
@RequestMapping("/v1/hms/authenticate")
public class AutenticateController {

	@Autowired
	Authenticate authenticateService;

	@PostMapping
	public Response<Profile> authenticateUser(@RequestBody Profile profile) {
		boolean isAuthenticated = authenticateService.authenticateUser(profile);
		if (isAuthenticated) {
			return new Response<Profile>(HmsConstants.STATUS_SUCCESS, HmsConstants.SUCCESS_MESSAGE, null);
		} else {
			return new Response<Profile>(HmsConstants.STATUS_FAILED, HmsConstants.NO_RECORDS_FOUND, null);
		}
	}
}
