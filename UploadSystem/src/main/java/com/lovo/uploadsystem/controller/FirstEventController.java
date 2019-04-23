package com.lovo.uploadsystem.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovo.uploadsystem.entity.EventAreaEntity;
import com.lovo.uploadsystem.entity.EventTypeEntity;
import com.lovo.uploadsystem.entity.FirstEventEntity;
import com.lovo.uploadsystem.entity.FormKeyEntity;
import com.lovo.uploadsystem.entity.FormValueEntity;
import com.lovo.uploadsystem.service.IEventAreaService;
import com.lovo.uploadsystem.service.IEventTypeService;
import com.lovo.uploadsystem.service.IFirstEventService;
import com.lovo.uploadsystem.service.IFormValueService;
import com.lovo.uploadsystem.util.JSONChange;
@RequestMapping("event/")
@Controller
public class FirstEventController{
	
	@Autowired
	private IFirstEventService firstEventService;//事件初报服务对象
	
	@Autowired
	private IEventAreaService eventAreaService;//事件地区服务对象
	
	@Autowired
	private IEventTypeService eventTypeService;//事件类型服务对象
	
	@Autowired
	private IFormValueService formValueService;
	
	@Autowired
    private JmsMessagingTemplate jmsTemplate;
	
	@RequestMapping("getEventState")
	public ModelAndView getEventState(String state) {
		ModelAndView mv = new ModelAndView("home");
		
		int eventState = Integer.parseInt(state);
		List<FirstEventEntity> list =  firstEventService.findFirstEventByEventState(eventState);
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping("first")
	public ModelAndView loginF() {
		ModelAndView mv = new ModelAndView("first_report");
		return mv;
	}
	
	@RequestMapping("getArea")
	@ResponseBody
	public List<EventAreaEntity> getArea() {
		List<EventAreaEntity> areaList = eventAreaService.getArea();
		return areaList;
	}
	
	@RequestMapping("getType")
	@ResponseBody
	public List<EventTypeEntity> getType() {
		List<EventTypeEntity> typeList = eventTypeService.getType();
		return typeList;
	}
	
	@RequestMapping("getKey")
	@ResponseBody
	public List<FormKeyEntity> getKey(String typeId) {
		EventTypeEntity eventType = eventTypeService.findType(typeId);
		List<FormKeyEntity> keyList = new ArrayList<>(eventType.getKeySet());
		
		return keyList;
	}
	
	@RequestMapping("saveEvent")
	public ModelAndView saveEvent(FirstEventEntity event,FormValueEntity formValue, String eventTypeId,String eventAreaId,String btnName) throws JsonProcessingException {
		//获取事件类型编码
		EventTypeEntity eventType = eventTypeService.findType(eventTypeId);
		String typeCode = eventType.getTypeCode();
		//生成事件编号
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String dateStr = df.format(new Date());
        
        String firstEventNo = typeCode + dateStr + (int)(Math.random()*100+1);
		//保存事件
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df1.format(new Date());
        EventAreaEntity eventArea = eventAreaService.findArea(eventAreaId);
        
        event.setEventDatetime(date);
        event.setFirstEventNo(firstEventNo);
        event.setEventType(eventType);
        event.setEventArea(eventArea);
        
        Set<FormValueEntity> valueSet = new HashSet<>();
        valueSet.add(formValue);
        event.setValueSet(valueSet);
        
        firstEventService.saveEvent(event);
        
        formValue.setFirstEvent(event);
        
        formValueService.saveValue(formValue);
        
		if(btnName.equals("保存并上报")) {
			//上传事件
			event.setEventState(2);
			Destination destination = new ActiveMQQueue("testQueue");
			
			String message = JSONChange.objToJson(event);
			
	        //将消息放入队列
	        jmsTemplate.convertAndSend(destination, message);
		}
		return null;
	}
	
}
