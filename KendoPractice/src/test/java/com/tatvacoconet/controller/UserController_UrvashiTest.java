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
import com.tatvacoconet.entity.UserUrvashi;

@RunWith(SpringRunner.class)  
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserController_UrvashiTest {
	public static String[] techArr = {"Java",".net"};
	public static UserUrvashi user = new UserUrvashi("Test Urvashi JUnit", "TestTest","123@gmail.com","address1","India","female","JAVA, .Net",new Date(),1234,"123.png",techArr);
	public static final String success = "success";

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void test1Example() {
		String body = this.restTemplate.getForObject("/userHello_Urvashi?username=Urvashi", String.class);
		assertThat(body).isEqualTo("Hello from Urvashi ...");
	}
	
	@Test
	public void test2UserSave_urvashi(){
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/saveUser", user, String.class);
		String resp = responseEntity.getBody(); 
		
		JSONObject json = new JSONObject(resp);		
		if(json != null)	user.setId(json.getInt("userId"));
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertThat(json.getString("success")).isEqualTo(success);
	}
	
	@Test
	public void test3UserUpdate_urvashi(){
		user.setUsername("Test Urvashi JUnit - Edited");
		
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/updateUser", user, String.class);
		String status = responseEntity.getBody(); 
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertThat(status).contains(success);
	}
	
	@Test
	public void test4DeleteUser_urvashi(){
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/deleteUser", new String(String.valueOf(user.getId())), String.class);
		String status = responseEntity.getBody(); 
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertThat(status).contains(success);
	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void test5UsersListData_urvashi(){
		ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/listUser", String.class);
		String userList = responseEntity.getBody();
		
		Gson gsonReceiver = new Gson();
		List<UserUrvashi> users= gsonReceiver.fromJson(userList, List.class);
		
		assertThat(users.size()).isGreaterThanOrEqualTo(0);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	
}
