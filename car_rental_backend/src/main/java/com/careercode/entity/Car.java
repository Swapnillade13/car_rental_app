package com.careercode.entity;

import java.util.Date;

import com.careercode.dbo.CarDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cars")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String name;
	
	private String brand;
	
	private String color;
	
	private String type;
	
	private String transmission;
	
	private Long price;
	
	private String description;
	
	private Date year;
	
	@Column(columnDefinition = "longblob")
	private byte[] image;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public CarDto getCarDto() {
		CarDto carDto = new CarDto();
		carDto.setId(Id);
		carDto.setReturnedImge(image);
		carDto.setBrand(brand);
		carDto.setColor(color);
		carDto.setDescription(description);
		carDto.setName(name);
		carDto.setType(type);
		carDto.setYear(year);
		carDto.setTransmission(transmission);
		carDto.setPrice(price);
		
		return carDto;
	}
	
}
