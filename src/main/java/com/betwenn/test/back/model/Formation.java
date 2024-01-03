package com.betwenn.test.back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="formations")
public class Formation {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String address;
	private String zipCode;
	private String city;
	private String phone;
	private String romeCode;
	
	// Constructors
	public Formation() {
		super();
	}

	public Formation(Long id, String title, String address, String zipCode, String city, String phone, String romeCode) {
		super();
		this.id = id;
		this.title = title;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.phone = phone;
		this.romeCode = romeCode;
	}

	public Formation(String title, String address, String zipCode, String city, String phone, String romeCode) {
		super();
		this.title = title;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.phone = phone;
		this.romeCode = romeCode;
	}

	// Getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAdress() {
		return address;
	}

	public void setAdress(String adress) {
		this.address = adress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRomeCode() {
		return romeCode;
	}

	public void setRomeCode(String romeCode) {
		this.romeCode = romeCode;
	}	
	
}
