package com.spring.api.hms.service;

import com.spring.api.hms.model.Profile;

public interface Authenticate {

	boolean authenticateUser(Profile profile);
}
