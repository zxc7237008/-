package com.lovo.uploadsystem.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lovo.uploadsystem.entity.ContinueEntity;
import com.lovo.uploadsystem.entity.FirstEventEntity;
import com.lovo.uploadsystem.entity.PageBean;
import com.lovo.uploadsystem.service.IContinueService;




@Controller
public class ContinueController {

	@Autowired
	public IContinueService continueService;
	
	
	
	
	
	
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
	public ModelAndView saveContinue(ContinueEntity continueEntity, String eventNo) {
		
		//将续报信息存入数据库
		
		 continueService.saveContinueEntity(continueEntity,eventNo);
		
		
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
