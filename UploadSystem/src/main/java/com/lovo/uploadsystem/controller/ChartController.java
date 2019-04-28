package com.lovo.uploadsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lovo.uploadsystem.dto.PieDto;
import com.lovo.uploadsystem.service.IEventTypeService;
import com.lovo.uploadsystem.service.IFirstEventService;

@Controller
public class ChartController {
	@Autowired
	private IFirstEventService firstEventService;
	@Autowired
	private IEventTypeService eventTypeService;
	
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
	
	@ResponseBody
	@RequestMapping("getEventByArea")
	private int getEventByArea(String areaId) {
		
		int eventNum = firstEventService.findAllventNumByArea(areaId);
		
		return eventNum;
	}
	
	@ResponseBody
	@RequestMapping("getEventByType")
	private Map<String, Object> getEventByType() {
		
		Map<String, Object> map = firstEventService.findAllEventNumByType();
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("getEventByTime")
	private List<PieDto> getEventByTime(String beginTime, String endTime) {
		
		List<Integer> eventNum = firstEventService.findAllEventNumByTime();
		
		PieDto pto1 = new PieDto(eventNum.get(0),"第一季度");
		
		PieDto pto2 = new PieDto(eventNum.get(1),"第二季度");
		
		PieDto pto3 = new PieDto(eventNum.get(2),"第三季度");
		
		PieDto pto4 = new PieDto(eventNum.get(3),"第四季度");
		
		List<PieDto> pList = new ArrayList<>();
		
		pList.add(pto1);
		pList.add(pto2);
		pList.add(pto3);
		pList.add(pto4);
		
		return pList;
		
	}
	
}
