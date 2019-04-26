package com.lovo.uploadsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("eventType/")
@Controller
public class EventTypeController {

	@RequestMapping("info")
	public ModelAndView eventType() {
		ModelAndView mv = new ModelAndView("event_type");
		return mv;
	}
	
	@RequestMapping("update")
	public String updateType() {
		return "update_event_type";
	}
	
	@RequestMapping("add")
	public String addType() {
		return "add_event_type";
	}
}
