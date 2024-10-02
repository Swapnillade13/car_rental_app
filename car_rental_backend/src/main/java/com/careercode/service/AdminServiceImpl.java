package com.careercode.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careercode.dbo.CarDto;
import com.careercode.entity.Car;
import com.careercode.repository.CarRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CarRepository carRepository;

	@Override
	public boolean postCar(CarDto carDto) throws IOException {
		
		try {
			Car car = new Car();
			car.setImage(carDto.getImage().getBytes());
			car.setBrand(carDto.getBrand());
			car.setColor(carDto.getColor());
			car.setDescription(carDto.getDescription());
			car.setName(carDto.getName());
			car.setType(carDto.getType());
			car.setYear(carDto.getYear());
			car.setTransmission(carDto.getTransmission());
			car.setPrice(carDto.getPrice());
			
			carRepository.save(car);
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}

	@Override
	public List<CarDto> getAllCars() {
		return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
	}

	@Override
	public void deleteCar(Long id) {
		carRepository.deleteById(id);
		
	}

	@Override
	public CarDto getCarById(Long id) {
		Optional<Car> optionalCar = carRepository.findById(id);
		if(optionalCar != null && optionalCar.isPresent()) {
			return optionalCar.get().getCarDto();
		}
		return null;
	}

	@Override
	public boolean updateCar(Long id, CarDto carDto) throws Exception {
		Optional<Car> optionalCar = carRepository.findById(id);
		if(optionalCar != null && optionalCar.isPresent()) {
			Car existingCar = optionalCar.get();
			if(existingCar.getImage() != null) {
				existingCar.setImage(carDto.getImage().getBytes());
			}
			
			existingCar.setBrand(carDto.getBrand());
			existingCar.setColor(carDto.getColor());
			existingCar.setDescription(carDto.getDescription());
			existingCar.setName(carDto.getName());
			existingCar.setType(carDto.getType());
			existingCar.setYear(carDto.getYear());
			existingCar.setTransmission(carDto.getTransmission());
			existingCar.setPrice(carDto.getPrice());
			
			carRepository.save(existingCar);
			return true;
		}
		return false;
	}
	
	
}
