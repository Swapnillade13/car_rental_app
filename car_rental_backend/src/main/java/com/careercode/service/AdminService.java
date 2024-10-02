package com.careercode.service;

import java.io.IOException;
import java.util.List;

import com.careercode.dbo.CarDto;

public interface AdminService {

	boolean postCar(CarDto carDto) throws IOException;
	
	List<CarDto> getAllCars();
	
	void deleteCar(Long id);
	
	CarDto getCarById(Long id);
	
	boolean updateCar(Long id, CarDto carDto) throws Exception;
}
