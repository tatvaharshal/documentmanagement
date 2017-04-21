package com.tatvacoconet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author TatvaSoft
 *
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String dashboardLoader(){
		logger.info("Main Page Loading");
		return "index";
	}
}
