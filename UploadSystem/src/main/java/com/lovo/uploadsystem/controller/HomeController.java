package com.lovo.uploadsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.lovo.uploadsystem.dto.FirstEventAreaEventTypeDTO;
import com.lovo.uploadsystem.dto.PowerDto;
import com.lovo.uploadsystem.entity.EventAreaEntity;
import com.lovo.uploadsystem.entity.EventTypeEntity;
import com.lovo.uploadsystem.entity.FirstEventEntity;
import com.lovo.uploadsystem.entity.PageBean;
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
	
	@ResponseBody
	@RequestMapping("/findEventTypeLists")
	public PageBean<FirstEventAreaEventTypeDTO> findEventTypeLists(int pageNum,String typeName,
			String eventLevel,String areaName,String eventState) {
		//分页
        int pageSize = 5;
		
        //查询所有信息的分页数
        int pageAll = firstEventService.getFirstEventAllPages(typeName, eventLevel, areaName, eventState,pageSize);
        List<FirstEventAreaEventTypeDTO> dtoList = firstEventService.findAllFirstEventsByPage(typeName, eventLevel, areaName, eventState, pageNum, pageSize);
        PageBean<FirstEventAreaEventTypeDTO> pageBean = new PageBean<>();
        pageBean.setPageNum(pageNum);
        pageBean.setPageAll(pageAll);
        pageBean.setList(dtoList);
		
		return pageBean;
	}
	
	@ResponseBody
	@RequestMapping("/findUNInformation")
	public String findUNInformation(String eventState) {
		int eventStateInt = Integer.parseInt(eventState);
		List<FirstEventEntity> list = firstEventService.findFirstEventByEventState(eventStateInt);
		String num = String.valueOf(list.size());
		return num;
	}
	
	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");
		//查询所有的事件类型集合
		List<EventTypeEntity> eventlist =  eventTypeService.findAllEventTypes();
		//查询所有地区集合
		List<EventAreaEntity> areaList = eventAreaService.findAllEventAreas();
		//查询所有事件的状态
		List<StateEntity> stateList = firstEventService.findAllFirstEventStates();
		
		//分页
		int pageNum = 0;
        int pageSize = 5;
		
        //查询所有信息的分页数
        int pageAll = firstEventService.getFirstEventAllPages("事件类型","事件等级","区域","事件状态",pageSize);
        List<FirstEventAreaEventTypeDTO> dtoList = firstEventService.findAllFirstEventsByPage("事件类型","事件等级","区域","事件状态", pageNum, pageSize);
        PageBean<FirstEventAreaEventTypeDTO> pageBean = new PageBean<>();
        
        pageBean.setPageNum(pageNum);
        pageBean.setPageAll(pageAll);
        pageBean.setList(dtoList);
        
        mv.addObject("eventlist",eventlist);
        mv.addObject("areaList",areaList);
        mv.addObject("stateList",stateList);
        mv.addObject("dtoList",dtoList);
		
		return mv;
	}
	
}
