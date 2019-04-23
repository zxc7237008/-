package com.lovo.uploadsystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lovo.uploadsystem.entity.JournalEntity;
import com.lovo.uploadsystem.entity.PageBean;
import com.lovo.uploadsystem.service.IJournalService;





@Controller
public class JournalController {
	@Autowired
	private IJournalService ijournalService;
	
		
		@RequestMapping("showjournalListPage")
	    public ModelAndView showUserListPage(){
	        ModelAndView modelAndView = new ModelAndView("journal");
	        int pageNum = 0;
	        int pageSize = 5;
	        int pageAll = ijournalService.getAllPage(pageSize);
	        List<JournalEntity> list = ijournalService.showjournalListPage(pageNum,pageSize);
	        PageBean<JournalEntity> pageBean = new PageBean<>();
	        pageBean.setList(list);
	        pageBean.setPageNum(pageNum);
	        pageBean.setPageAll(pageAll);
	        modelAndView.addObject("pageBean",pageBean);
	        return modelAndView;
	    }
	 
		
	    @RequestMapping("JournalchangePage")
	    @ResponseBody
	    public PageBean changePage(int pageNum){
	        int pageSize = 5;
	        List<JournalEntity> list = ijournalService.showjournalListPage(pageNum,pageSize);
	        int pageAll = ijournalService.getAllPage(pageSize);
	        PageBean<JournalEntity> pageBean = new PageBean<>();
	        pageBean.setList(list);
	        pageBean.setPageAll(pageAll);
	        pageBean.setPageNum(pageNum);
	       return  pageBean;
	    }


	  

}
