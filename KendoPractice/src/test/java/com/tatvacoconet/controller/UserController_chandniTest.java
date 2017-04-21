package com.tatvacoconet.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.apache.catalina.User;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tatvacoconet.entity.UserMasterChandni;

@RunWith(SpringRunner.class)  
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserController_chandniTest {
	
	public static UserMasterChandni userObj = new UserMasterChandni(445, "Test","UserChandni", "123456",(byte) 0, "chandniuser@test.test", "9845454545", "Img (1).png", "Cricket,Tennis", "Ahmedabad", "Test Address 1, Address 2", new Date(), new Date(),new Date());
	public static final String successMessage = "Success";
	public static final String deletedMessage = "Deleted";
	
	private Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void test1Example() {
		String body = this.restTemplate.getForObject("/chandni/hello-user?username=Chandni", String.class);
		assertThat(body).isEqualTo("Welcome Chandni");
	}
	
	
	@Test
	public void test2UserSave_chandni(){
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/chandni/createUser", userObj, String.class);
		ResponseEntity<UserMasterChandni> userObjTemp = this.restTemplate.getForEntity("/chandni/getUserByEmail?email=" + userObj.getUserEmail(), UserMasterChandni.class);
		userObj.setUserId(userObjTemp.getBody().getUserId());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void test3GetUserDetailById_chandni(){
		ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/chandni/findUserByEmail?email=" + userObj.getUserEmail(), String.class);
		String response = responseEntity.getBody(); 

		assertThat(response).isEqualTo("true");
	}
	
	@Test
	public void test4UserUpdate_chandni(){
		userObj.setUserFirstName("Chandni");
		userObj.setUserLastName("TestJUnit");
		
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/chandni/updateUser", userObj, String.class);	
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void test5DeleteUser_chandni(){
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/chandni/deleteUser", new String(String.valueOf(userObj.getUserId())), String.class);
		String status = responseEntity.getBody(); 
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertThat(status).contains(deletedMessage);
	}
	
	@Test
	public void test6UsersListData_chandni(){
		ResponseEntity<List> responseEntity = this.restTemplate.getForEntity("/chandni/chandniGetUserList", List.class);
		List<UserMasterChandni> userObjList = responseEntity.getBody();
		
		assertThat(userObjList.size()).isGreaterThanOrEqualTo(0);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void test7UsersByCity_chandni(){
		ResponseEntity<List> responseEntity = this.restTemplate.getForEntity("/chandni/getUsersByCity", List.class);
		List<String> userObjList = responseEntity.getBody();
		
		assertThat(userObjList.size()).isGreaterThanOrEqualTo(0);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
