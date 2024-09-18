package com.careercode.service;

import com.careercode.dbo.SingupRequest;
import com.careercode.dbo.UserDto;

public interface AuthService {

	UserDto createCustomer(SingupRequest singupRequest);
	boolean hasCustomerAlreadyExist(String email);
}
