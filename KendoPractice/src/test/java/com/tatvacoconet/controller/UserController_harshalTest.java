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
import com.tatvacoconet.entity.UserMaster_harshal;

@RunWith(SpringRunner.class)  
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserController_harshalTest {
	
	public static UserMaster_harshal umh = new UserMaster_harshal("Test Harshal JUnit", "TestTest", "testtest@test.test", "Male", "Cricket, Football, Chess", "Ahmedabad", "Test Society, Test City", new Date(), 1111111111L, "test123.jpg");
	public static final String success = "success";
	public static final String deleted = "Deleted";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void test1Example() {
		String body = this.restTemplate.getForObject("/user_harshal/userHello_harshal?username=_HarShaL_", String.class);
		assertThat(body).isEqualTo("Hello _HarShaL_ - Lolz ...");
	}
	
	@Test
	public void test2UserSave_harshal(){
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/user_harshal/userSave_harshal", umh, String.class);
		String resp = responseEntity.getBody(); 
		
		JSONObject json = new JSONObject(resp);		
		if(json != null)	umh.setId(json.getInt("userId"));
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertThat(json.getString("success")).isEqualTo(success);
	}
	
	@Test
	public void test3GetUserDetailById_harshal(){
		ResponseEntity<UserMaster_harshal> responseEntity = this.restTemplate.postForEntity("/user_harshal/getUserDetailById_harshal", new String(String.valueOf(umh.getId())), UserMaster_harshal.class);
		UserMaster_harshal userMaster_harshal = responseEntity.getBody(); 
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertThat(umh.getFullname()).isEqualTo(userMaster_harshal.getFullname());
		assertThat(umh.getId()).isEqualTo(userMaster_harshal.getId());
	}
	
	@Test
	public void test4UserUpdate_harshal(){
		umh.setFullname("Test Harshal JUnit - Edited");
		
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/user_harshal/userUpdate_harshal", umh, String.class);
		String status = responseEntity.getBody(); 
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertThat(status).contains(success);
	}
	
	@Test
	public void test5DeleteUser_harshal(){
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/user_harshal/deleteUser_harshal", new String(String.valueOf(umh.getId())), String.class);
		String status = responseEntity.getBody(); 
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertThat(status).contains(deleted);
	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void test6UsersListData_harshal(){
		ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/user_harshal/usersListData_harshal", String.class);
		String userMaster_harshals = responseEntity.getBody();
		
		Gson gsonReceiver = new Gson();
		List<UserMaster_harshal> umhs = gsonReceiver.fromJson(userMaster_harshals, List.class);
		
		assertThat(umhs.size()).isGreaterThanOrEqualTo(0);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void test7GetUserCountPerCity_harshal(){
		ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/user_harshal/getUserCountPerCity_harshal", String.class);
		String graphData = responseEntity.getBody();
		
		Gson gsonReceiver = new Gson();
		List graphDataList = gsonReceiver.fromJson(graphData, List.class);
		
		assertThat(graphDataList.size()).isGreaterThanOrEqualTo(0);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
}
