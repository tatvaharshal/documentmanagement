package com.tatvacoconet.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.tatvacoconet.entity.UserMasterChirag;

@RunWith(SpringRunner.class)  
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserController_JUNIT_Chirag {
	
	public static UserMasterChirag userMasterChirag = new UserMasterChirag("Chirag Purohit", "chirag@test.com", "Chirag!&789", 1, new Date(),"ck.jpeg", 123456789, "Cricket, Football, Chess", "Jamnagar", "GOTA", "","");
	public static final String success = "success";
	public static final String deleted = "Deleted";
	
	public static final String URL = "/userChirag/";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	
	/**
	 *  Save: Adding new user record in DB.
	 */
	@Test
	public void Test1_Save(){
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(URL+"saveUser", userMasterChirag, String.class);
		String resp = responseEntity.getBody(); 
		JSONObject json = new JSONObject(resp);		
		if(json != null)	
			userMasterChirag.setUserid(json.getLong("userId"));
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertThat(json.getString("success")).isEqualTo(success);
	}
	
	
	
	/**
	 *  List by Userid: Getting Edit page to edit by Userid.
	 */
	/*@Test
	public void Test3_GetUserById(){
		ResponseEntity<UserMasterChirag> responseEntity = this.restTemplate.postForEntity(URL+"getUser/{id}",  userMasterChirag.getUserid(), UserMasterChirag.class);
		UserMasterChirag userMasterChirag = responseEntity.getBody(); 
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertThat(userMasterChirag.getFname()).isEqualTo(userMasterChirag.getFname());
		assertThat(userMasterChirag.getUserid()).isEqualTo(userMasterChirag.getUserid());
	}*/
	
	
	/**
	 *  List : Display all users.
	 */
	@Test
	public void Test4_GetUserList(){
		ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(URL+"getUserList",  String.class);		
		String userMasterChirag = responseEntity.getBody();
		Gson gsonReceiver = new Gson();
		List<UserMasterChirag> userMaster = gsonReceiver.fromJson(userMasterChirag, List.class);
		assertThat(userMaster.size()).isGreaterThanOrEqualTo(0);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	
	/**
	 *  Update:  Update user record from DB by Userid.
	 */
	@Test
	public void Test5_Update(){
		userMasterChirag.setFname("Test Chirag JUnit - Edited");
		
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(URL+"updateUser", userMasterChirag, String.class);
		String status = responseEntity.getBody(); 
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertThat(status).contains(success);
	}
	
	/**
	 *  Delete: Delete user record from DB.
	 */
	@Test
	public void Test2_Delete(){
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(URL+"deleteUser", userMasterChirag.getUserid(), String.class);
		String status = responseEntity.getBody(); 
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertThat(status).contains(deleted);
	}
	
	
}
