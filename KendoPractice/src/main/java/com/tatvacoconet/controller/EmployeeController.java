package com.tatvacoconet.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.tatvacoconet.entity.EmployeeMasterParth;
import com.tatvacoconet.service.IEmployeeServiceParth;


/**
 * 
 * @author TatvaSoft
 *
 */
@Controller
@RequestMapping("/")
public class EmployeeController {

	private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private IEmployeeServiceParth employeeServiceParth;
	
	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/addemployee", method = RequestMethod.GET)
	public ModelAndView addEmployee() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("employeeid", 0);
		mav.setViewName("AddEmployee");
		return mav;
	}

	@RequestMapping(value = "/employeelist", method = RequestMethod.GET)
	public String employeeList() {
		return "EmployeeList";
	}

	@RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
	public ResponseEntity<String> createEmployee(@RequestBody EmployeeMasterParth employeeMaster) {
		logger.info(employeeMaster.getBirthDate().toString());
		if (employeeMaster != null) {
			if (employeeMaster.getEmployeeId() > 0) {
				employeeServiceParth.update(employeeMaster);
			} else {
				employeeServiceParth.create(employeeMaster);
			}
		}

		logger.info("Employee created successfully - employeeId : {}");
		return new ResponseEntity<String>(new Gson().toJson("success"), HttpStatus.OK);
	}

	@RequestMapping(value = "/getallemployee", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeMasterParth>> getAllEmployees() {
		return new ResponseEntity<List<EmployeeMasterParth>>(employeeServiceParth.getEmployeeMasterList(),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteemployee", method = RequestMethod.POST)
	public ResponseEntity<String> createEmployee(@RequestBody Integer employeeId) {
		logger.info(employeeId.toString());
		employeeServiceParth.delete(employeeId);

		logger.info("Employee created successfully - employeeId : {}");
		return new ResponseEntity<String>(new Gson().toJson("success"), HttpStatus.OK);
	}

	@RequestMapping(value = "/editemployee", method = RequestMethod.GET)
	public ModelAndView editEmployee(@RequestParam(value = "employeeId", required = false) String employeeId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("employeeid", Integer.parseInt(employeeId));
		mav.setViewName("AddEmployee");
		return mav;
	}

	@RequestMapping(value = "/getemployeebyid", method = RequestMethod.POST)
	public ResponseEntity<EmployeeMasterParth> getEmployeeById(@RequestBody Integer employeeId) {
		EmployeeMasterParth employee = employeeServiceParth.find(employeeId);
		return new ResponseEntity<EmployeeMasterParth>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "/employeechart", method = RequestMethod.GET)
	public String employeeChart() {
        return "EmployeeChart";
	}
	
	
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public String imageUpload(@RequestParam("file") MultipartFile file) throws IOException {
	System.out.println("file >>>>>> "+file.getSize());
	String uploadedFileName = file.getOriginalFilename();
	
	// Take uploaded file
	String path =  servletContext.getRealPath("/resources/images");
	String filename=file.getOriginalFilename();  
	
	System.out.println(path+" "+filename);  
	File uploadedFile = new File(path+"/"+filename);
	boolean tempFileUploaded = uploadedFile.createNewFile();
	
	if(tempFileUploaded){
		// Temporary upload file to server
		FileOutputStream fos = new FileOutputStream(uploadedFile);
		fos.write(file.getBytes());
		fos.close();
	}
	return "hi";
	}
	
}
