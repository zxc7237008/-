package com.lovo.uploadsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RosterController {
	
   @RequestMapping("roster")
   public String roster() {
	   return"roster";
	   
   }
}
