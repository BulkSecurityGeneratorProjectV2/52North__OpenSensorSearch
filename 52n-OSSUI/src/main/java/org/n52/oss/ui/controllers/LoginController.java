package org.n52.oss.ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@RequestMapping(value="/login")
	public String login(ModelMap map){
		return "login/login";
	}
	
	
	

}
