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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tatvacoconet.entity.UserMasterChirag;
import com.tatvacoconet.service.IUserChiragService;

/**
 * 
 * @author TatvaSoft
 *
 */
@RestController
@RequestMapping(value = "/userChirag")
public class UserControllerChirag {

	private Logger logger = LoggerFactory.getLogger(UserControllerChirag.class);

	@Autowired
	private IUserChiragService userService;

	@Autowired
	ServletContext servletContext;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
	public Long getEditPage(@PathVariable("id") Long userid) {
		logger.info("userid 11111111 >>>>>>>>>>> {} ", userid);
		return userid;
	}
	
	/**
	 * This API will redirect the useruration widget page.
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/userList", method = RequestMethod.GET) 
	public ModelAndView displayUserList() {
		ModelAndView mav = new ModelAndView();
		logger.info("userList called >>>>>>>>>>>");
		mav.setViewName("userListChirag");
		return mav;
	}
	
	
	/**
	 * This API will redirect the useruration widget page.
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET) 
	public ModelAndView dashboard() {
		ModelAndView mav = new ModelAndView();
		logger.info("Dashboard >>>>>>>>>>>");
		mav.setViewName("dashboardChirag");
		return mav;
	}
	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET) 
	public ModelAndView displayAddUser() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("addUserChirag");
		return mav;
	}

	/**
	 * This API will get all the customized widgets.
	 * @return List<UserMaster>
	 */	
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	public ResponseEntity<List<UserMasterChirag>> getUserList() {
		List<UserMasterChirag> list = userService.findAll();		
		if(list.isEmpty()){
			return new ResponseEntity<List<UserMasterChirag>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserMasterChirag>>(list, HttpStatus.OK);
	}

	
	
	/**
	 * This API will SAVE the customized widget data in DB.
	 * @return String
	 */	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ResponseEntity<String> saveUser(@RequestBody UserMasterChirag userMaster) {	
		
		JsonObject json = new JsonObject();
		
		if (userService.isUserExist(userMaster)) {
			logger.info("User with name " + userMaster.getFname()
					+ " already exist");
			json.addProperty("conflict", "conflict");
			json.addProperty("userId", userMaster.getUserid());
			return new ResponseEntity<String>(new Gson().toJson(json),HttpStatus.CONFLICT);
		}
		
		
		if(userMaster != null){
			userService.saveUser(userMaster);
		}else{
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		
		
		json.addProperty("userId", userMaster.getUserid());
		json.addProperty("success", "success");
		
		logger.info("User saved successfully - userId : {}", userMaster.getUserid()); 
		return new ResponseEntity<String>(new Gson().toJson(json), HttpStatus.OK);
	}

	
	
	/**
	 * This API will update widget from id.
	 * @return String
	 */	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ResponseEntity<String> updateUser(@RequestBody UserMasterChirag userMaster) {
		
		userService.updateUser(userMaster);
		logger.info("User updated successfully - userid : {}", userMaster.getUserid());
		return new ResponseEntity<String>(new Gson().toJson("success"), HttpStatus.OK);
	}


	/**
	 * This API will get widget from id.
	 * @return String
	 */	
	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<UserMasterChirag> getUserMaster(@PathVariable("id") Long userid) {
		UserMasterChirag userMasterMaster = userService.findbyId(userid);
		return new ResponseEntity<UserMasterChirag>(userMasterMaster, HttpStatus.OK);
	}


	/**
	 * This API will delete widgets from the dashboard.
	 * @return String
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> deleteUsers(@RequestBody Long userid) {

			userService.deleteUser(userid);
		logger.info("User deleted successfully - userid : {}", userid);
		return new ResponseEntity<String>(new Gson().toJson("Deleted"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
	
		// Take uploaded file
		String path =  servletContext.getRealPath("/resources/images");
		String fileName=file.getOriginalFilename();  
		
		logger.info(">>>>>>>>> Path & File Name : {} {}",path+" >> "+fileName);  
		File uploadedFile = new File(path+"/"+fileName);
		boolean tempFileUploaded = uploadedFile.createNewFile();
		
		if(tempFileUploaded){
			// Temporary upload file to server
			FileOutputStream fos = new FileOutputStream(uploadedFile);
			fos.write(file.getBytes());
			fos.close();
		}
		return "success";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getCharts", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getCharts() {
		
		List userCityCountList = userService.getCharts();
		
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