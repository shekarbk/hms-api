package com.spring.api.hms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.hms.constants.HmsConstants;
import com.spring.api.hms.response.Response;

@RestController
@RequestMapping("/v1/hms/healthcheck")
public class HealthCheckController {

	@GetMapping("/ping")
	public Response<String> ping() {
		String message= "system is up and running";
		return new Response<String>(HmsConstants.STATUS_SUCCESS, HmsConstants.UPDATE_MESSAGE, message);
	}
}
