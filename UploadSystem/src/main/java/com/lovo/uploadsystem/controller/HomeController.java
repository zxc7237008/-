package com.lovo.uploadsystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lovo.uploadsystem.entity.EventAreaEntity;
import com.lovo.uploadsystem.entity.EventTypeEntity;
import com.lovo.uploadsystem.entity.FirstEventEntity;
import com.lovo.uploadsystem.entity.StateEntity;
import com.lovo.uploadsystem.service.IEventAreaService;
import com.lovo.uploadsystem.service.IEventTypeService;
import com.lovo.uploadsystem.service.IFirstEventService;

@Controller
public class HomeController {
	@Autowired
	private IEventTypeService eventTypeService;
	@Autowired
	private IEventAreaService eventAreaService;
	@Autowired
	private IFirstEventService firstEventService;
	
	@RequestMapping("/findEventTypeLists")
	public ModelAndView findEventTypeLists() {
		ModelAndView mv = new ModelAndView("home");
		
		//查询所有的事件类型集合
		List<EventTypeEntity> eventlist =  eventTypeService.findAllEventTypes();
		//查询所有地区集合
		List<EventAreaEntity> areaList = eventAreaService.findAllEventAreas();
		//查询所有事件的状态
		List<StateEntity> stateList = firstEventService.findAllFirstEventStates();

		mv.addObject("eventlist", eventlist);
		mv.addObject("areaList", areaList);
		mv.addObject("stateList", stateList);
		
		return mv;
	}
	@RequestMapping("/findFirstEventsByEventTypeEventLevelEventAreaState")
	public ModelAndView findFirstEventsByEventTypeEventLevelEventAreaState(String typeName,
			String eventLevel,String areaName,String eventState) {
		ModelAndView mv = new ModelAndView("home");
		List<FirstEventEntity>  list = firstEventService.findFirstEventsByEventTypeEventLevelEventAreaState(typeName, eventLevel, areaName, eventState);
		
		mv.addObject("list", list);
		return mv;
		
	}
	
	/*@RequestMapping("/home")
	public String home() {
		return "home";
	}*/
	
}
