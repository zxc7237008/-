package com.lovo.uploadsystem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lovo.uploadsystem.entity.JournalEntity;
import com.lovo.uploadsystem.entity.PageBean;
import com.lovo.uploadsystem.entity.ShiftexchangeEntity;
import com.lovo.uploadsystem.entity.UserEntity;
import com.lovo.uploadsystem.service.IShiftexchangeService;
import com.netflix.client.http.HttpRequest;
import com.sun.research.ws.wadl.Request;

@Controller
public class ShiftexchangeController {

	@Autowired
	private IShiftexchangeService iShiftexchangeService;
	
	private String Uname;
	

	//交班按钮
	@RequestMapping("shiftexchange")
    public ModelAndView shiftexchange(String password,HttpServletRequest request){
		 ModelAndView modelAndView=null;
	 	   HttpSession session =request.getSession(); 
	 	   UserEntity userEntity = (UserEntity) session.getAttribute("User");
	 	   System.out.println("交班方法里面"+userEntity.getUname());//占时不知道名字
	 	   System.out.println("交班方法里面"+password);
	 	   String userpassword= userEntity.getUpasss();
		
		
		if(password.equals(userpassword)){
	         modelAndView = new ModelAndView("login");
	         //删除session里面存在的用户
	         session.removeAttribute("User");
			}else {
				ModelAndView modelAndView1 = new ModelAndView();
				RedirectView rv = new RedirectView("gotoJournal");//重定向的视图
				modelAndView1.setView(rv);
				 return modelAndView1;
			}		
		
		System.out.println("返回了视图");
        return modelAndView;
    }
	
	@RequestMapping("saveshiftexchangecontent")
    public ModelAndView saveshiftexchangecontent(String daily, HttpServletRequest request){
		 
 	   HttpSession session =request.getSession(); 
 	   UserEntity userEntity = (UserEntity) session.getAttribute("User");
 	   System.out.println(userEntity.getUname());//占时不知道名字
		 ModelAndView modelAndView=new ModelAndView();
		 ShiftexchangeEntity shiftexchangeEntity = new ShiftexchangeEntity();
		shiftexchangeEntity.setName(userEntity.getUname());	
		shiftexchangeEntity.setDaily(daily);
		iShiftexchangeService.savejournal(shiftexchangeEntity);
			RedirectView rv = new RedirectView("gotoJournal");//重定向的视图
			modelAndView.setView(rv);
		
        return modelAndView;
    }
	
	
	
	@RequestMapping("gotolog")
    public ModelAndView gotolog(){
        ModelAndView modelAndView = new ModelAndView("log");
        int pageNum = 0;
        int pageSize = 5;
        int pageAll = iShiftexchangeService.getAllPage(pageSize);
        List<ShiftexchangeEntity> list = iShiftexchangeService.showshiftexListPage(pageNum,pageSize);
        PageBean<ShiftexchangeEntity> pageBean = new PageBean<>();
        pageBean.setList(list);
        pageBean.setPageNum(pageNum);
        pageBean.setPageAll(pageAll);
        modelAndView.addObject("pageBean",pageBean);
        return modelAndView;
    }
	
	
	 @RequestMapping("shiftexchangePage")
	    @ResponseBody
	    public PageBean changePage(int pageNum,String too){
	    	 List<ShiftexchangeEntity> list=null;
	    	 int pageSize = 5;
	    	 PageBean<ShiftexchangeEntity> pageBean = new PageBean<>();
	    	if(too!=null&&!too.equals("")){
	    		list = iShiftexchangeService.showshiftexListPageto(too, pageNum, pageSize);
	    		 int pageAll = iShiftexchangeService.getAllPageto(too, pageSize);
	 	        pageBean.setList(list);
	 	        pageBean.setPageAll(pageAll);
	 	        pageBean.setPageNum(pageNum);
	    	}else{
	    		 list = iShiftexchangeService.showshiftexListPage(pageNum,pageSize);
	    		 int pageAll = iShiftexchangeService.getAllPage(pageSize);
	 	        pageBean.setList(list);
	 	        pageBean.setPageAll(pageAll);
	 	        pageBean.setPageNum(pageNum);
			}
	       
	       return  pageBean;
	    }
}
