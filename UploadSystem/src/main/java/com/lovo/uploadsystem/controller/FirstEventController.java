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
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovo.uploadsystem.dto.KeyAndValueDto;
import com.lovo.uploadsystem.dto.NoDealWithDto;
import com.lovo.uploadsystem.entity.EventAreaEntity;
import com.lovo.uploadsystem.entity.EventTypeEntity;
import com.lovo.uploadsystem.entity.FirstEventEntity;
import com.lovo.uploadsystem.entity.FormKeyEntity;
import com.lovo.uploadsystem.entity.FormValueEntity;
import com.lovo.uploadsystem.service.IEventAreaService;
import com.lovo.uploadsystem.service.IEventTypeService;
import com.lovo.uploadsystem.service.IFirstEventService;
import com.lovo.uploadsystem.service.IFormValueService;
import com.lovo.uploadsystem.util.FirstEvent2DtoUtil;
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
	private IFormValueService formValueService;//表单值服务对象
	
	@Autowired
    private JmsMessagingTemplate jmsTemplate;//用于向MQ发送信息
	
	@RequestMapping("getEventState")
	public ModelAndView getEventState(String state) {
		ModelAndView mv = new ModelAndView("home");
		
		int eventState = Integer.parseInt(state);
		List<FirstEventEntity> list =  firstEventService.findFirstEventByEventState(eventState);
		mv.addObject("list", list);
		
		return mv;
	}
	
	/**
	 * 请求到添加事件初报
	 * @return 添加事件初报视图
	 */
	@RequestMapping("first")
	public ModelAndView loginF() {
		ModelAndView mv = new ModelAndView("first_report");
		return mv;
	}
	
	@RequestMapping("update")
	public ModelAndView update(String eventId) {
		ModelAndView mv = new ModelAndView("update_first");
		FirstEventEntity event = firstEventService.findEvent(eventId);
		mv.addObject("event",event);
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
	
	@RequestMapping("getValue")
	@ResponseBody
	public FormValueEntity getValue(String eventId) {
		FormValueEntity value = formValueService.findValue(eventId);
		return value;
	}
	
	@RequestMapping("getKeyAndValue")
	@ResponseBody
	public KeyAndValueDto getKeyAndValue(String eventId,String typeId) {
		FormValueEntity value = formValueService.findValue(eventId);
		EventTypeEntity eventType = eventTypeService.findType(typeId);
		List<FormKeyEntity> keyList = new ArrayList<>(eventType.getKeySet());
		KeyAndValueDto dto = new KeyAndValueDto(keyList, value);
		return dto;
	}
	
	@RequestMapping("saveEvent")
	public ModelAndView saveEvent(FirstEventEntity event,FormValueEntity formValue, String eventTypeId,String eventAreaId,String btnName) throws JsonProcessingException {
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView("/home");
		mv.setView(rv);
		
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
        event.setEventState(1);
        
        Set<FormValueEntity> valueSet = new HashSet<>();
        valueSet.add(formValue);
        event.setValueSet(valueSet);
        
        firstEventService.saveEvent(event);
        
        formValue.setFirstEvent(event);
        
        formValueService.saveValue(formValue);
        
       
        
		if(btnName.equals("保存并上报")) {
			//上传事件
			List<FormKeyEntity> keyList = new ArrayList<>(eventType.getKeySet());
		    FormKeyEntity keys = keyList.get(0);
			event.setEventState(2);
			
			firstEventService.saveEvent(event);
			
			Destination destination = new ActiveMQQueue("uploadEventMessage");
			
			NoDealWithDto dto = FirstEvent2DtoUtil.firstEvent2Dto(event, keys, formValue);
			
			String message = JSONChange.objToJson(dto);
			
	        //将消息放入队列
	        jmsTemplate.convertAndSend(destination, message);
		}
		return mv;
	}
	
	@RequestMapping("updateEvent")
	public ModelAndView updateEvent(FirstEventEntity event,FormValueEntity formValue, String eventTypeId,String eventAreaId,String btnName) throws JsonProcessingException {
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView("/home");
		mv.setView(rv);
		
		EventTypeEntity eventType = eventTypeService.findType(eventTypeId);
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df1.format(new Date());
        EventAreaEntity eventArea = eventAreaService.findArea(eventAreaId);
		event.setEventDatetime(date);
        event.setEventType(eventType);
        event.setEventArea(eventArea);
		event.setEventState(1);
		
		Set<FormValueEntity> valueSet = new HashSet<>();
        valueSet.add(formValue);
        event.setValueSet(valueSet);
        
        firstEventService.saveEvent(event);
        
        formValue.setFirstEvent(event);
        
        //删除修改前的表单值
        formValueService.delValue(event.getFirstEventNo());
        //添加修改后的表单值
        formValueService.saveValue(formValue);
        
        if(btnName.equals("修改并上报")) {
        	//上传事件
        	List<FormKeyEntity> keyList = new ArrayList<>(eventType.getKeySet());
		    FormKeyEntity keys = keyList.get(0);
			event.setEventState(2);
			
			firstEventService.saveEvent(event);
			
			Destination destination = new ActiveMQQueue("uploadEventMessage");
			
			NoDealWithDto dto = FirstEvent2DtoUtil.firstEvent2Dto(event, keys, formValue);
			
			String message = JSONChange.objToJson(dto);
			
	        //将消息放入队列
	        jmsTemplate.convertAndSend(destination, message);
		}
		return mv;
	}
	
	@RequestMapping("info")
	public ModelAndView infoEvent(String eventId) {
		ModelAndView mv = new ModelAndView("info_first");
		FirstEventEntity event = firstEventService.findEvent(eventId);
		mv.addObject("event",event);
		return mv;
	}
	
	@RequestMapping("upload")
	public ModelAndView upload(String eventId) throws JsonProcessingException {
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView("/home");
		mv.setView(rv);
		
		FirstEventEntity event = firstEventService.findEvent(eventId);
		
		Set<FormValueEntity> valueSet = event.getValueSet();
		FormValueEntity formValue = valueSet.iterator().next();
		
		//上传事件
    	List<FormKeyEntity> keyList = new ArrayList<>(event.getEventType().getKeySet());
	    FormKeyEntity keys = keyList.get(0);
		event.setEventState(2);
		
		firstEventService.saveEvent(event);
		
		Destination destination = new ActiveMQQueue("uploadEventMessage");
		
		NoDealWithDto dto = FirstEvent2DtoUtil.firstEvent2Dto(event, keys, formValue);
		
		String message = JSONChange.objToJson(dto);
		
        //将消息放入队列
        jmsTemplate.convertAndSend(destination, message);
		return mv;
	}
	
	@RequestMapping("del")
	public ModelAndView delEvent(String eventId) {
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView("/home");
		mv.setView(rv);
		//删除关联的表单值
		formValueService.delValue(eventId);
		//删除事件
		firstEventService.delEvent(eventId);
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("test")
	public String test(String eventId) {
		Destination destination = new ActiveMQQueue("sendMessageToUploadSystem");
		String message = eventId;
		jmsTemplate.convertAndSend(destination, message);
		return "first_report";
	}
	
}
