package com.lovo.uploadsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ChartController {
	@RequestMapping("showChart")
	private ModelAndView showChart() {

		ModelAndView mv = new ModelAndView("chart");
		
		return mv;
		
	}
	
	@RequestMapping("map")
	private ModelAndView map() {

		ModelAndView mv = new ModelAndView("map");
		
		return mv;
		
	}
	

}
