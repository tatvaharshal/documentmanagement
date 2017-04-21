package com.tatvacoconet.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tatvacoconet.entity.UserUrvashi;
import com.tatvacoconet.service.IUserServiceUrvashi;

/**
 * 
 * @author PCA50 URVASHI
 *
 */
@Controller
public class UserControllerUrvashi {

	private Logger logger = LoggerFactory.getLogger(UserControllerUrvashi.class);
	@Autowired
	private IUserServiceUrvashi userService;

	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/userHello_Urvashi", method = RequestMethod.GET)
	public ResponseEntity<String> userHello_harshal(@RequestParam(value = "username", required = false) String username) {
		String helloUser = "Hello from "+username+" ...";
		return new ResponseEntity<String>(helloUser, HttpStatus.OK);
	}


	@RequestMapping(value = "/addUserUrvashi", method = RequestMethod.GET)
	public ModelAndView addUserUrvashi(@RequestParam(value="id",required=false) String id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("addUserUrvashi");
		if(id != null){
			UserUrvashi user = userService.getUserById(Integer.parseInt(id));
			String tech = "["+user.getTechnology()+"]";
			user.setTechnology(tech);
			mav.addObject("edituser",user);
			mav.addObject("techArray",tech);
		}
		logger.info("addUserUrvashi Page Loading");
		return mav;
	}

	@RequestMapping(value = "/insertUserUrvashi", method = RequestMethod.GET)
	public String insertUserUrvashi(){
		logger.info("Insert new user");
		return "addUserUrvashi";
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ResponseEntity<String> saveUser(@RequestBody UserUrvashi user) {
		String temp =Arrays.toString(user.getTechArray());
		user.setTechnology(temp.replace("[","").replace("]", ""));
		userService.save(user);
		JsonObject json = new JsonObject();
		json.addProperty("userId", user.getId());
		json.addProperty("success", "success");
		
		return new ResponseEntity<String>(new Gson().toJson(json), HttpStatus.OK);
	}

	@RequestMapping(value = "/UsersListUrvashi", method = RequestMethod.GET)
	public String UsersListUrvashi(){
		logger.info("UsersList  Page Loading");
		return "UsersListUrvashi";
	}

	@RequestMapping(value = "/listUser", method = RequestMethod.GET)
	public ResponseEntity<List<UserUrvashi>> listUser() {
		List<UserUrvashi> list = userService.listUser();
		return new ResponseEntity<List<UserUrvashi>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ResponseEntity<String> deleteUser(@RequestBody String id) {
		userService.deleteUser(Integer.parseInt(id));
		return new ResponseEntity<String>(new Gson().toJson("success"),HttpStatus.OK);
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ResponseEntity<String> updateUser(@RequestBody UserUrvashi user) {
		String temp =Arrays.toString(user.getTechArray());
		user.setTechnology(temp.replace("[","").replace("]", ""));
		userService.updateUser(user);
		return new ResponseEntity<String>(new Gson().toJson("success"),HttpStatus.OK);
	}


	@RequestMapping(value = "/userImageUploadUrvashi", method = RequestMethod.POST)
	public String userImageUpload(@RequestParam("file") MultipartFile file) throws IOException {
		logger.info("file >>>>>> " + file.getSize());

		// Take uploaded file
		String path = servletContext.getRealPath("/resources/image_urvashi");
		String filename = file.getOriginalFilename();

		logger.info(path + " " + filename);
		File uploadedFile = new File(path+"/"+filename);
		boolean tempFileUploaded = uploadedFile.createNewFile();

		if (tempFileUploaded) {
			// Temporary upload file to server
			FileOutputStream fos = new FileOutputStream(uploadedFile);
			fos.write(file.getBytes());
			fos.close();
		}
		return "profile_image_uploaded";
	}

}
