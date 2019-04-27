package com.lovo.uploadsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lovo.uploadsystem.entity.EventTypeEntity;
import com.lovo.uploadsystem.entity.FormKeyEntity;
import com.lovo.uploadsystem.entity.PageBean;
import com.lovo.uploadsystem.service.IEventTypeService;
import com.lovo.uploadsystem.service.IFormKeyService;

@RequestMapping("eventType/")
@Controller
public class EventTypeController {
	
	@Autowired
	private IEventTypeService eventTypeService;
	
	@Autowired
	private IFormKeyService formKeyService;
	
	@RequestMapping("info")
	public ModelAndView eventType() {
		ModelAndView mv = new ModelAndView("event_type");
		return mv;
	}
	
	@RequestMapping("update")
	public ModelAndView update(String typeId) {
		ModelAndView mv = new ModelAndView("update_event_type");
		
		EventTypeEntity type =eventTypeService.findType(typeId);
		String typeName = type.getTypeName();
		mv.addObject("typeId",typeId);
		mv.addObject("typeName",typeName);
		return mv;
	}
	
	@RequestMapping("add")
	public String add() {
		return "add_event_type";
	}
	
	@RequestMapping("addType")
	public ModelAndView addType(String typeName, String typeCode, String[] keys) {
		ModelAndView mv = new ModelAndView("event_type");
		
		EventTypeEntity eventType = new EventTypeEntity();
		eventType.setTypeName(typeName);
		eventType.setTypeCode(typeCode);
		//保存事件类型
		eventType = eventTypeService.saveType(eventType);
		
		//解析keys
		FormKeyEntity key = new FormKeyEntity();
		switch (keys.length) {
		case 6:
			key.setKey6(keys[5]);
		case 5:
			key.setKey5(keys[4]);
		case 4:
			key.setKey4(keys[3]);
		case 3:
			key.setKey3(keys[2]);
		case 2:
			key.setKey2(keys[1]);
		case 1:
			key.setKey1(keys[0]);
			break;
		}
		//保存表单
		key.setEventType(eventType);
		formKeyService.saveKey(key);
		
		return mv;
	}
	
	@RequestMapping("updateType")
	public ModelAndView updateType(EventTypeEntity t,FormKeyEntity keys) {
		ModelAndView mv = new ModelAndView("event_type");
		
		//删除修改前的表单
		formKeyService.delKey(t.getTypeId());
		
		//保存事件类型
		EventTypeEntity type = eventTypeService.findType(t.getTypeId());
		eventTypeService.saveType(type);
		
		//保存修改后的表单
		keys.setEventType(type);
		formKeyService.saveKey(keys);
		return mv;
	}
	
	@RequestMapping("getType")
	public PageBean<EventTypeEntity> getType(int pageNum){
		PageBean<EventTypeEntity> page = eventTypeService.findTypeByPage(pageNum,5);
		return page;
	}
}
