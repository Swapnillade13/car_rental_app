package com.careercode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careercode.repository.CarRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CarRepository carRepository;
	
}
