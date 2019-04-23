package com.lovo.uploadsystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lovo.uploadsystem.entity.EventAreaEntity;
import com.lovo.uploadsystem.entity.EventTypeEntity;
import com.lovo.uploadsystem.entity.FirstEventAreaEventTypeDTO;
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
		
		List<FirstEventAreaEventTypeDTO> dtoList1 = firstEventService.findAllFirstEvents(1);
		List<FirstEventAreaEventTypeDTO> dtoList2 = firstEventService.findAllFirstEvents(2);
		List<FirstEventAreaEventTypeDTO> dtoList3 = firstEventService.findAllFirstEvents(3);

		mv.addObject("eventlist", eventlist);
		mv.addObject("areaList", areaList);
		mv.addObject("stateList", stateList);
		mv.addObject("dtoList1", dtoList1);
		mv.addObject("dtoList2", dtoList2);
		mv.addObject("dtoList3", dtoList3);
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/findFirstEventsByEventTypeEventLevelEventAreaState")
	public List<FirstEventAreaEventTypeDTO> findFirstEventsByEventTypeEventLevelEventAreaState(String typeName,
			String eventLevel,String areaName,String eventState) {
		List<FirstEventAreaEventTypeDTO>  list = firstEventService.findFirstEventsByEventTypeEventLevelEventAreaState(typeName, eventLevel, areaName, eventState);
		
		return list;
		
	}
	
	@ResponseBody
	@RequestMapping("/findUNInformation")
	public String findUNInformation(String eventState) {
		int eventStateInt = Integer.parseInt(eventState);
		List<FirstEventEntity> list = firstEventService.findFirstEventByEventState(eventStateInt);
		String num = String.valueOf(list.size());
		return num;
	}
	
	/*@RequestMapping("/home")
	public String home() {
		return "home";
	}*/
	
}
