package com.tatvacoconet.controller;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
/*@RequestMapping("/")*/
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String dashboardLoader(){
		logger.info("Main Page Loading");
		return "index";
	}*/
    @RequestMapping(value = { "/", "/Welcome**" }, method = RequestMethod.GET)
    public ModelAndView welcomePage(ModelMap model, Principal principal) {

        String loggedInUserName=principal.getName();
        return new ModelAndView("DocumentList", "userName", loggedInUserName);

    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView adminPage(ModelMap model, Principal principal) {
        String loggedInUserName=principal.getName();
        return new ModelAndView("index", "userName", loggedInUserName);

    }

    @RequestMapping(value = "/dba**", method = RequestMethod.GET)
    public ModelAndView dbaPage() {

        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        return model;

    }
   @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout) {
       ModelAndView model = new ModelAndView();
       if (error != null) {
           model.addObject("error", "Invalid username and password!");
       }

       if (logout != null) {
           model.addObject("msg", "You've been logged out successfully.");
       }
       model.setViewName("Login");

       return model;
    }

}
