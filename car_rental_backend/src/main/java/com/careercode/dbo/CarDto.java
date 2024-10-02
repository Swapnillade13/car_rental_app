package com.careercode.dbo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class CarDto {

	private Long Id;
	
	private String name;
	
	private String brand;
	
	private String color;
	
	private String type;
	
	private String transmission;
	
	private Long price;
	
	private String description;
	
	private Date year;
	
	private MultipartFile image;
	
	private byte[] returnedImge;

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

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public byte[] getReturnedImge() {
		return returnedImge;
	}

	public void setReturnedImge(byte[] returnedImge) {
		this.returnedImge = returnedImge;
	}

}
