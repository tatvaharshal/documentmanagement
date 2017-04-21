package com.tatvacoconet.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author TatvaSoft
 *
 */
@Entity
@Table(name="employee_parth")
public class EmployeeMasterParth {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Employee_Id")
	private int employeeId;

	@Column(name = "UserName", nullable = false, length = 500)
	private String userName;
	
	@Column(name = "Password", nullable = false, length = 100)
	private String password;

	@Column(name = "Email", nullable = false)
	private String email;
	
	@Column(name = "Address")
	private String address;
	
	@Column(name = "Country")
	private String country;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "Technologies")
	private String technologies;
	
	@Column(name = "BirthDate")
	private Date birthDate;
	
	@Column(name = "PhoneNumber")
	private String phoneNumber;	
	
	@Column(name = "ImageFilePath")
	private String imageFilePath;	
	
	@CreationTimestamp
	@Column(name = "CreatedDate")
	private Date createdOn;
	
	@UpdateTimestamp
	@Column(name = "UpdatedDate")
	private Date updatedOn;
	
	public EmployeeMasterParth() {
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
	
	public String getTechnologies() {
		return technologies;
	}
	
	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			this.birthDate = formatter.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }		
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getImageFilePath() {
		return imageFilePath;
	}
	
	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}
