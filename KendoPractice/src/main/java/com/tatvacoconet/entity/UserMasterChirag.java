package com.tatvacoconet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * For Table user_mst
 */
@Entity
@Table(name = "user_mst_chirag")
public class UserMasterChirag implements java.io.Serializable {

	/* userId, PK */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid", nullable = false, unique = true)
	protected Long userid;

	/* fName */
	@Column(name = "fName", nullable = false, length = 255)
	protected String fname;

	/* emailId */
	@Column(name = "emailId", nullable = false, length = 75)
	protected String emailid;

	/* pwd */
	@Column(name = "pwd", nullable = false, length = 20)
	protected String pwd;

	/* gender */
	@Column(name = "gender", nullable = false, length = 20)
	protected long gender;

	/* birthDate */
	@Column(name = "birthDate")
	protected Date birthdate;

	/* img */
	@Column(name = "imageFilePath", nullable = true, length = 500)
	protected String imageFilePath;

	/* phone */
	@Column(name = "phone", nullable = false, length = 20)
	protected long phone;

	/* primaryRegId */
	@Column(name = "hobbies", nullable = true, length = 255)
	protected String hobbies;

	/* userLevel */
	@Column(name = "city", nullable = true, length = 255)
	protected String city;

	/* address */
	@Column(name = "address", nullable = false)
	protected String address;

	/* createdDate */
	@Column(name = "createdDate", nullable = true, length = 75)
	protected String createddate;

	/* updatedDate */
	@Column(name = "updatedDate", nullable = true, length = 75)
	protected String updateddate;

	public UserMasterChirag() {

	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public long getGender() {
		return gender;
	}

	public void setGender(long gender) {
		this.gender = gender;
	}

	

	
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
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

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(String updateddate) {
		this.updateddate = updateddate;
	}

	public UserMasterChirag(String fname, String emailid, String pwd, long gender, Date birthdate,
			String imageFilePath, long phone, String hobbies, String city, String address, String createddate,
			String updateddate) {
		this.fname = fname;
		this.emailid = emailid;
		this.pwd = pwd;
		this.gender = gender;
		this.birthdate = birthdate;
		this.imageFilePath = imageFilePath;
		this.phone = phone;
		this.hobbies = hobbies;
		this.city = city;
		this.address = address;
		this.createddate = createddate;
		this.updateddate = updateddate;
	}
	

}
