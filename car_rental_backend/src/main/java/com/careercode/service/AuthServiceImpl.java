package com.careercode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.careercode.dbo.SingupRequest;
import com.careercode.dbo.UserDto;
import com.careercode.entity.User;
import com.careercode.enums.UserRole;
import com.careercode.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void createAdminUser() {
		User admin = userRepository.findByUserRole(UserRole.ADMIN);
		if(admin == null) {
			User newAdmin = new User();
			newAdmin.setEmail("admin@test.com");
			newAdmin.setName("Admin");
			newAdmin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			newAdmin.setUserRole(UserRole.ADMIN);
			userRepository.save(newAdmin);
			System.out.println("Admin user created successfully!!!");
		}
	}

	@Override
	public UserDto createCustomer(SingupRequest singupRequest) {
		User user = new User();
		user.setEmail(singupRequest.getEmail());
		user.setName(singupRequest.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(singupRequest.getPassword()));
		user.setUserRole(UserRole.CUSTOMER);
		User createdUser = userRepository.save(user);
		UserDto userDto = new UserDto();
		userDto.setId(createdUser.getId());
		return userDto;
	}

	@Override
	public boolean hasCustomerAlreadyExist(String email) {
		return userRepository.findByEmail(email).isPresent();
	}
	
	
}
