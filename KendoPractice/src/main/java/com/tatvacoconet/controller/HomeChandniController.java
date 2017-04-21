package com.tatvacoconet.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tatvacoconet.entity.UserMasterChandni;
import com.tatvacoconet.service.IUserChandniService;

/**
 * 
 * @author TatvaSoft
 *
 */
@Controller
@RequestMapping("chandni/")
public class HomeChandniController {

	private Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private IUserChandniService userService;
	
	@RequestMapping(value = "/hello-user", method = RequestMethod.GET)
	public ResponseEntity<String> HelloUser(@RequestParam(value = "username", required = false) String username) {
		String text = "Welcome " + username;
		return new ResponseEntity<String>(text, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chandni-user-list", method = RequestMethod.GET)
	public String userList()
	{		
		return "chandniUserList";
	}
	
	@RequestMapping(value = "/chandni-charts", method = RequestMethod.GET)
	public String userCharts()
	{		
		return "chandniUserDashboard";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getUsersByCity", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getUsersByCity() {
		
		List userCityCountList = userService.getUsersByCity("userCity");
		
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
		return new ResponseEntity<List<String>>(responseList, HttpStatus.OK);
	}
	
	/**
	 * This API will get all the users.
	 * @return List<UserMasterChandni>
	 */	
	@RequestMapping(value = "/chandniGetUserList", method = RequestMethod.GET)
	public ResponseEntity<List<UserMasterChandni>> getUserList() {
		List<UserMasterChandni> list = userService.getUserList();		
		if(list.isEmpty()){
			return new ResponseEntity<List<UserMasterChandni>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserMasterChandni>>(list, HttpStatus.OK);
	}
	
	/**
	 * This API will delete user
	 * @return String
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> deleteUser(@RequestBody String userId) {

		if(userId!=null & !userId.isEmpty()){
			int id=Integer.parseInt(userId);
			userService.deleteUser(id);
		}
		logger.info("User deleted successfully - userId : {}", userId);
		return new ResponseEntity<String>(new Gson().toJson("Deleted"), HttpStatus.OK);
	}
	
	/**
	 * This API will get user from id.
	 * @return UserMasterChandni
	 */	
	@RequestMapping(value = { "/chandni-user-form" }, method = RequestMethod.GET)
	public ModelAndView userForm(@RequestParam(value="id", required=false) String id){
		logger.info(">>>>>>>>>>>>>>>>>>>>id " + id);
		ModelAndView mav = new ModelAndView();
		if(id != null){
			UserMasterChandni model = userService.find(Integer.parseInt(id));
			mav.addObject("userChandni",model);
			mav.setViewName("chandniUserform");
		}
		else {
			UserMasterChandni model = new UserMasterChandni();
			model.setUserGender(Byte.parseByte("0"));
			mav.setViewName("chandniUserform");
		}
		return mav;
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> createUser(@RequestBody UserMasterChandni model) 
	{
		if(model!=null){
			userService.createUser(model);
		}
		return new ResponseEntity<String>(new Gson().toJson("success"), HttpStatus.OK);
	}

	/**
	 * This API will update user from id.
	 * @return String
	 */	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ResponseEntity<String> updateUser(@RequestBody UserMasterChandni user) {
		userService.updateUser(user);
		return new ResponseEntity<String>(new Gson().toJson("success"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findUserByEmail", method = RequestMethod.GET)
	public ResponseEntity<String> findUserByEmail(@RequestParam(value="email") String email) {
		UserMasterChandni response = userService.findUserByEmail(email);
		if(response != null){
			return new ResponseEntity<String>(new Gson().toJson(true), HttpStatus.OK);
		}
		return new ResponseEntity<String>(new Gson().toJson(false), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUserByEmail", method = RequestMethod.GET)
	public ResponseEntity<UserMasterChandni> getUserByEmail(@RequestParam(value="email") String email) {
		UserMasterChandni response = userService.findUserByEmail(email);
		return new ResponseEntity<UserMasterChandni>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/userImageUpload", method = RequestMethod.POST)
	public ResponseEntity<String> userImageUpload(@RequestParam("file") MultipartFile file) throws IOException {
		// Take uploaded file
		String path = servletContext.getRealPath("/resources/images_chandni");
		String filename = file.getOriginalFilename();

		logger.info(path + " " + filename);
		File uploadedFile = new File(path + "/" + filename);
		boolean tempFileUploaded = uploadedFile.createNewFile();

		if (tempFileUploaded) {
			// Temporary upload file to server
			FileOutputStream fos = new FileOutputStream(uploadedFile);
			fos.write(file.getBytes());
			fos.close();
		}
		return new ResponseEntity<String>(new Gson().toJson("success"), HttpStatus.OK);
	}
}

