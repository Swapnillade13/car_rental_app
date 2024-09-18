package com.careercode.controller;

import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careercode.dbo.AuthenticationRequest;
import com.careercode.dbo.AuthenticationResponse;
import com.careercode.dbo.SingupRequest;
import com.careercode.dbo.UserDto;
import com.careercode.entity.User;
import com.careercode.repository.UserRepository;
import com.careercode.service.AuthService;
import com.careercode.service.UserService;
import com.careercode.utils.JWTUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping("/singup")
	public ResponseEntity<?> singupCustomer(@RequestBody SingupRequest singupRequest) {
		if(authService.hasCustomerAlreadyExist(singupRequest.getEmail())) {
			return new ResponseEntity<>("user already exist !!!", HttpStatus.NOT_ACCEPTABLE);
		}
		UserDto createdCustomerDto = authService.createCustomer(singupRequest);
		if(createdCustomerDto == null) {
			return new ResponseEntity<>("user not created, please try after sometime!!!", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(createdCustomerDto, HttpStatus.ACCEPTED);
		}
		
	}
	
	@PostMapping("/login")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) 
			throws BadRequestException, DisabledException, UsernameNotFoundException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), 
					authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username and password");
		}
		final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
		Optional<User> optionalUser = userRepository.findByEmail(authenticationRequest.getEmail());
		final String jwtToken = jwtUtil.generateToken(userDetails);
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		if(optionalUser.isPresent()) {
			authenticationResponse.setJwt(jwtToken);
			authenticationResponse.setUserId(optionalUser.get().getId());
			authenticationResponse.setUserRole(optionalUser.get().getUserRole());
		}
		
		return authenticationResponse;
		
	}
}
