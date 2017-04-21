package com.tatvacoconet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="user_urvashi")
public class UserUrvashi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "country")
	private String country;

	@Column(name = "gender")
	private String gender;

	@Column(name = "technology")
	private String technology;

	@Column(name = "date_of_birth")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-mm-dd")
	private Date dateOfBirth;

	@Column(name = "phone")
	private int phone;

	@Column(name = "image")
	private String image;

	@Transient
	private String[] techArray;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String[] getTechArray() {
		return techArray;
	}

	public void setTechArray(String[] techArray) {
		this.techArray = techArray;
	}
	public UserUrvashi() {
		// TODO Auto-generated constructor stub
	}
	
	public UserUrvashi(String username, String password, String email,
			String address, String country, String gender, String technology,
			Date dateOfBirth, int phone, String image, String[] techArray) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.country = country;
		this.gender = gender;
		this.technology = technology;
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
		this.image = image;
		this.techArray = techArray;
	}
	

}
