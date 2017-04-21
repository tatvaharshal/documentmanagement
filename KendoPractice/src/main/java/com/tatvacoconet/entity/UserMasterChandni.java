package com.tatvacoconet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author TatvaSoft
 *
 */
 @Entity
 @Table(name="user_chandni")
 public class UserMasterChandni
 {	
 	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Byte getUserGender() {
		return userGender;
	}

	public void setUserGender(Byte userGender) {
		this.userGender = userGender;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getUserDob() {
		return userDob;
	}

	public void setUserDob(Date userDob) {
		this.userDob = userDob;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserHobbies() {
		return userHobbies;
	}

	public void setUserHobbies(String userHobbies) {
		this.userHobbies = userHobbies;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
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

	public UserMasterChandni() {
	}
	
	public UserMasterChandni(int userId,String userFirstName, String userLastName, String userPassword, Byte userGender, String userEmail,
			String userPhoneNumber, String userImage, String userHobbies, String userCity, String userAddress, Date createdOn, Date updatedOn, Date dob) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userPassword = userPassword;
		this.userGender = userGender;
		this.userEmail = userEmail;
		this.userPhoneNumber = userPhoneNumber;
		this.userImage = userImage;
		this.userHobbies = userHobbies;
		this.userCity = userCity;
		this.userAddress = userAddress;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.userDob = dob;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
 	@Column(name = "user_id")
 	private int userId;
 	
 	@Column(name = "user_first_name", length = 50, nullable = false)
 	private String userFirstName;
 	
 	@Column(name = "user_last_name", nullable = false, length = 50)
 	private String userLastName;
 		
 	@Column(name = "user_password", nullable = false, length = 25)
 	private String userPassword;
 	
 	@Column(name = "user_gender", nullable = false)
 	private Byte userGender;
 
 	@Column(name = "user_email", nullable = false, length = 254)
 	private String userEmail;
 	
 	@Column(name = "user_dob")
 	private Date userDob;
 	
 	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	@Column(name = "user_image", columnDefinition = "TEXT", length = 255)
 	private String userImage;
 	
 	@Column(name = "user_phone_number", length = 15)
 	private String userPhoneNumber;
 	
 	@Column(name = "user_hobbies", length = 255)
 	private String userHobbies;
 	
 	@Column(name = "user_city", length = 100)
 	private String userCity;
 	
 	@Column(name = "user_address", columnDefinition = "TEXT", length = 500)
 	private String userAddress;

	@Column(name = "user_createdon")
	private Date createdOn;

	@Column(name = "user_updatedon")
	private Date updatedOn;
 } 
 