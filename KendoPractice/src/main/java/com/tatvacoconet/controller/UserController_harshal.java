package com.tatvacoconet.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tatvacoconet.entity.UserMaster_harshal;
import com.tatvacoconet.service.IUserService_harshal;

/**
 * 
 * @author TatvaSoft
 *
 */
@Controller
@RequestMapping("/user_harshal")
public class UserController_harshal {
	
	private Logger logger = LoggerFactory.getLogger(UserController_harshal.class);
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private IUserService_harshal userService_harshal;
	
	@RequestMapping(value = "/userHello_harshal", method = RequestMethod.GET)
	public ResponseEntity<String> userHello_harshal(@RequestParam(value = "username", required = false) String username) {
		String helloUser = "Hello " + username + " - Lolz ...";
		return new ResponseEntity<String>(helloUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/usersList_harshal", method = RequestMethod.GET)
	public ModelAndView usersList_harshal_Loader(){
		ModelAndView mav = new ModelAndView("usersList_harshal");
		logger.info("usersList_harshal loading");
		return mav;
	}
	
	
	@RequestMapping(value = "/userForm_harshal", method = RequestMethod.GET)
	public ModelAndView userForm_harshal_Loader(){
		ModelAndView mav = new ModelAndView("userForm_harshal");
		logger.info("userForm_harshal loading");
		return mav;
	}
	
	@RequestMapping(value = "/editUserForm_harshal", method = RequestMethod.GET)
	public ModelAndView editUserForm_harshal(@RequestParam(value = "userId", required = false) String userId) {
		ModelAndView mav = new ModelAndView();
		logger.info("editUserForm_harshal : userId : {}", userId);
		if(userId != null && userId != "") {
			mav.addObject("userId", userId);		
		} 
		mav.setViewName("userForm_harshal");
		return mav;
	}
	
	
	@RequestMapping(value = "/usersListData_harshal", method = RequestMethod.GET)
	public ResponseEntity<List<UserMaster_harshal>> usersListData_harshal_Loader() {
		logger.info("usersListData_harshal loading");
		return new ResponseEntity<List<UserMaster_harshal>>(userService_harshal.findAllUsers_harshal(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/userImageUpload_harshal", method = RequestMethod.POST)
	public String userImageUpload_harshal(@RequestParam("file") MultipartFile file) throws IOException {
		logger.info("file >>>>>> " + file.getSize());
		String uploadedFileName = file.getOriginalFilename();

		// Take uploaded file
		String path = servletContext.getRealPath("/resources/images_harshal");
		String filename = uploadedFileName;

		logger.info(path + " " + filename);
		File uploadedFile = new File(path + "/" + filename);
		boolean tempFileUploaded = uploadedFile.createNewFile();

		if (tempFileUploaded) {
			// Temporary upload file to server
			FileOutputStream fos = new FileOutputStream(uploadedFile);
			fos.write(file.getBytes());
			fos.close();
		}
		return "profile_image_uploaded";
	}
	
	@RequestMapping(value = "/userSave_harshal", method = RequestMethod.POST)
	public ResponseEntity<String> userSave_harshal(@RequestBody UserMaster_harshal userMaster_harshal) {
		
		if(userMaster_harshal != null){
			userService_harshal.createUser_harshal(userMaster_harshal);
		}else{
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		
		JsonObject json = new JsonObject();
		json.addProperty("userId", userMaster_harshal.getId());
		json.addProperty("success", "success");
		
		logger.info("User saved successfully - userId : {}", userMaster_harshal.getId()); 
		return new ResponseEntity<String>(new Gson().toJson(json), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteUser_harshal", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> deleteUser_harshal(@RequestBody String userId) {

		if(userId != null & !userId.isEmpty()){
			userService_harshal.deleteUser_harshal(Integer.parseInt(userId));
		}
		logger.info("User deleted successfully - userId : {}", userId);
		return new ResponseEntity<String>(new Gson().toJson("Deleted"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUserDetailById_harshal", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<UserMaster_harshal> getUserDetailById_harshal(@RequestBody String userId) {
		UserMaster_harshal userMaster_harshal = userService_harshal.findUserById_harshal(Integer.parseInt(userId));
		return new ResponseEntity<UserMaster_harshal>(userMaster_harshal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/userUpdate_harshal", method = RequestMethod.POST)
	public ResponseEntity<String> userUpdate_harshal(@RequestBody UserMaster_harshal userMaster_harshal) {
		
		if(userMaster_harshal != null){
			userService_harshal.updateUser_harshal(userMaster_harshal);
		}else{
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		
		logger.info("User updated successfully - user id : {}", userMaster_harshal.getId()); 
		return new ResponseEntity<String>(new Gson().toJson("success"), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/userDashboard_harshal", method = RequestMethod.GET)
	public ModelAndView userDashboard_harshal(){
		ModelAndView mav = new ModelAndView("userDashboard_harshal");
		logger.info("userDashboard_harshal loading");
		return mav;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getUserCountPerCity_harshal", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getUserCountPerCity_harshal() {
		
		List userCityCountList = userService_harshal.getUserCountPerCity_harshal("city");
		
		StringBuilder cityData = new StringBuilder();
		StringBuilder userCount = new StringBuilder();
		
		cityData.append("[");
		userCount.append("[");
		for (int i = 0; i < userCityCountList.size(); i++)
		{
			Object[] row = (Object[]) userCityCountList.get(i);

			if (i == userCityCountList.size()-1)
			{
				cityData.append(" \"" + row[0] + "\" ");
				userCount.append(" \"" + row[1] + "\" ");
			}
			else
			{
				cityData.append(" \"" + row[0] + "\", ");
				userCount.append(" \"" + row[1] + "\", ");
			}
		}
		cityData.append("]");
		userCount.append("]");

		List<String> responseList = new ArrayList<String>();
		responseList.add(cityData.toString());
		responseList.add(userCount.toString());
		

		logger.info("User count per city get successfully"); 
		return new ResponseEntity<List<String>>(responseList, HttpStatus.OK);
	}
	
}
