package com.lovo.uploadsystem.controller;

import java.util.List;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovo.uploadsystem.dto.NeedResubmitDto;

import com.lovo.uploadsystem.entity.ContinueEntity;
import com.lovo.uploadsystem.entity.FirstEventEntity;
import com.lovo.uploadsystem.entity.PageBean;
import com.lovo.uploadsystem.service.IContinueService;
import com.lovo.uploadsystem.util.JSONChange;




@Controller
public class ContinueController {

	@Autowired
	public IContinueService continueService;//续报业务调用
	
	@Autowired
    private JmsMessagingTemplate jmsTemplate;//用于向MQ发送信息
	
	//查找详细信息
	@ResponseBody
	@RequestMapping("showMessage")
	public String showMessage(String id) {
		
		ContinueEntity c = continueService.showMessage(id);
		String message = c.getDetailed();
		

		return message;
	}
	
	
	
	
	//显示初报信息和续报信息
	@RequestMapping("showFirstEventMessage")
	public ModelAndView showFirstEventMessage(@RequestParam("firstEventNo") String firstEventNo) {
		FirstEventEntity eventList = continueService.findFirstEvent(firstEventNo);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("continue");
		mv.addObject("eventList", eventList);
		mv.addObject("firstEventNo",firstEventNo);
		
		int pageNum = 0;
        int pageSize = 5;
        int pageAll = continueService.getAllPage(pageSize,firstEventNo);
		List<ContinueEntity> continueList = continueService.findALLContinueEntity(firstEventNo,pageNum,pageSize);
		
		PageBean<ContinueEntity> pageBean = new PageBean<>();
        pageBean.setList(continueList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageAll(pageAll);
        mv.addObject("pageBean",pageBean);

		return mv;
	}
	
	//分页
	@RequestMapping("changePage")
    @ResponseBody
    public PageBean changePage(int pageNum,String eventNo){
		
        int pageSize = 5;
        List<ContinueEntity> continueList = continueService.findALLContinueEntity(eventNo,pageNum,pageSize);
        
        int pageAll = continueService.getAllPage(pageSize,eventNo);
        PageBean<ContinueEntity> pageBean = new PageBean<>();
        pageBean.setList(continueList);
        
        pageBean.setPageAll(pageAll);
        pageBean.setPageNum(pageNum);
       return  pageBean;
       
    }
	
	//添加续报
	@RequestMapping("saveContinue")
	public ModelAndView saveContinue(ContinueEntity continueEntity, String eventNo) throws JsonProcessingException {
		
		//将续报信息存入数据库
		ContinueEntity c = continueService.saveContinueEntity(continueEntity,eventNo);
		 
		//将消息放入队列
		 Destination destination = new ActiveMQQueue("testQueue");
		 NeedResubmitDto dto = new NeedResubmitDto(c.getrId(),eventNo,c.getLevel(),c.getNumber()+"",c.getDetailed(),c.getReporter(),c.getPhoneNumber(),c.getDate());
		 String message = JSONChange.objToJson(dto);
		 jmsTemplate.convertAndSend(destination, message);
		
		FirstEventEntity eventList = continueService.findFirstEvent(eventNo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("continue");
		mv.addObject("eventList", eventList);
		mv.addObject("firstEventNo",eventNo);
		
		int pageNum = 0;
        int pageSize = 5;
        int pageAll = continueService.getAllPage(pageSize,eventNo);
		List<ContinueEntity> continueList = continueService.findALLContinueEntity(eventNo,pageNum,pageSize);
		
		PageBean<ContinueEntity> pageBean = new PageBean<>();
        pageBean.setList(continueList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageAll(pageAll);
        mv.addObject("pageBean",pageBean);

		return mv;
	}
	
}
