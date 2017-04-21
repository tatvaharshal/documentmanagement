package com.tatvacoconet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * 
 * @author TatvaSoft
 *
 */
@Entity
@Table(name="user_harshal")
public class UserMaster_harshal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "user_fullname")
	private String fullname;
	
	@Column(name = "user_password")
	private String password;
	
	@Column(name = "user_email")
	private String email;
	
	@Column(name = "user_gender")
	private String gender;
	
	@Column(name = "user_hobby")
	private String hobby;
	
	@Column(name = "user_city")
	private String city;
	
	@Column(name = "user_address")
	private String address;
	
	@Column(name = "user_dateofbirth")
	private Date dateofbirth;
	
	@Column(name = "user_mobilenumber")
	private long mobilenumber;
	
	@Column(name = "user_profileimage")
	private String profileimage;
	
	@Column(name = "user_createdtime")
	@CreationTimestamp
	private Date createdtime;
	
	@Column(name = "user_updatedtime")
	@UpdateTimestamp
	private Date updatedtime;
	
	public UserMaster_harshal() {
	}
	
	public UserMaster_harshal(String fullname, String password, String email, String gender, String hobby, String city, String address, Date dateofbirth, long mobilenumber, String profileimage) {
		this.fullname = fullname;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.hobby = hobby;
		this.city = city;
		this.address = address;
		this.dateofbirth = dateofbirth;
		this.mobilenumber = mobilenumber;
		this.profileimage = profileimage;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public Date getUpdatedtime() {
		return updatedtime;
	}

	public void setUpdatedtime(Date updatedtime) {
		this.updatedtime = updatedtime;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public long getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getProfileimage() {
		return profileimage;
	}

	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}
	
	
}
