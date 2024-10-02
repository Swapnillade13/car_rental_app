package com.careercode.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careercode.dbo.CarDto;
import com.careercode.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/car")
	public ResponseEntity<?> postCar(@ModelAttribute CarDto carDto) throws IOException {
		boolean success = adminService.postCar(carDto);
		if(success) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/cars")
	public ResponseEntity<?> getCars() {
		return ResponseEntity.ok(adminService.getAllCars());
	}
	
	@DeleteMapping("/car/{id}")
	public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
		adminService.deleteCar(id);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/car/{id}")
	public ResponseEntity<CarDto> getCar(@PathVariable Long id) {
		return ResponseEntity.ok(adminService.getCarById(id));
	}
	
	@PutMapping("/car/{id}")
	public ResponseEntity<Void> updateCar(@PathVariable Long id, @ModelAttribute CarDto carDto) {
		try {
			boolean success = adminService.updateCar(id, carDto);
			if(success) return ResponseEntity.status(HttpStatus.OK).build();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
