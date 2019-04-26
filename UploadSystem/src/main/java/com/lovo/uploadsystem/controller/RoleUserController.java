package com.lovo.uploadsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;



@Controller
public class RoleUserController {

	@Autowired
	RestTemplate restTemplate;
	
	
	
	@RequestMapping("gotouser_role")
	public String gotouser_role(){
		return "user_role";
	}
}
