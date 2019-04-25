package com.lovo.uploadsystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lovo.uploadsystem.dto.YearDto;
import com.lovo.uploadsystem.entity.EventTypeEntity;
import com.lovo.uploadsystem.service.IEventTypeService;
import com.lovo.uploadsystem.service.IFirstEventService;
import com.lovo.uploadsystem.util.ListUtil;

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
	@RequestMapping("getEventByTypeAndTime")
	private List<Map<String, Object>> getEventByTypeAndTime(String year) {
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		List<YearDto> yearList = ListUtil.getYearList();
		
		List<EventTypeEntity> eventList = eventTypeService.findAllEventTypes();
		
		for (YearDto yearDto : yearList) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			String beginTime = year + yearDto.getBeginTime();
			
			String endTime = year + yearDto.getEndTime();
			
			for (EventTypeEntity eventTypeEntity : eventList) {
				
				String typeCode = eventTypeEntity.getTypeCode();
				
				map.put(eventTypeEntity.getTypeName(), firstEventService.findALLEventNumByTypeAndTime(beginTime, endTime, typeCode));
			}
			
			list.add(map);
			
		}
		
		return list;
		
	}
}
