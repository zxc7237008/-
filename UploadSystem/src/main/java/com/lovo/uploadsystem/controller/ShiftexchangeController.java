package com.lovo.uploadsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lovo.uploadsystem.entity.JournalEntity;
import com.lovo.uploadsystem.entity.PageBean;
import com.lovo.uploadsystem.entity.ShiftexchangeEntity;
import com.lovo.uploadsystem.service.IShiftexchangeService;
import com.netflix.client.http.HttpRequest;
import com.sun.research.ws.wadl.Request;

@Controller
public class ShiftexchangeController {

	@Autowired
	private IShiftexchangeService iShiftexchangeService;
	
	private String Uname;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
//	@RequestMapping("getshiftexchange")
//	public String getshiftexchange(String name,String password){
//	return	restTemplate.getForEntity("http://*", String.class,name,password).getBody();
//	}
//	
	
	@RequestMapping("shiftexchange")
    public ModelAndView shiftexchange(String password){
		 ModelAndView modelAndView=null;
//		 String passwordto = this.getshiftexchange();
		System.out.println(password);
		Uname="wenhao";
		if(password.equals(Uname)){
         modelAndView = new ModelAndView("*");
		}else{
			modelAndView = new ModelAndView("*");
		}
        return modelAndView;
    }
	
	@RequestMapping("saveshiftexchangecontent")
    public ModelAndView saveshiftexchangecontent(String daily){
//  	  HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
// 	   HttpSession session =request.getSession(); 
// 	   UserEntity userEntity = (UserEntity) session.getAttribute("User");
// 	   System.out.println(userEntity.getUname());//占时不知道名字
		 ModelAndView modelAndView=new ModelAndView();
		System.out.println(daily);
		Uname="duhao";
		ShiftexchangeEntity shiftexchangeEntity = new ShiftexchangeEntity();
		shiftexchangeEntity.setName(Uname);
		shiftexchangeEntity.setDaily(daily);
		iShiftexchangeService.savejournal(shiftexchangeEntity);
			RedirectView rv = new RedirectView("gotoJournal");//重定向的视图
			modelAndView.setView(rv);
		
        return modelAndView;
    }
	
	
	
	@RequestMapping("gotoregister")
    public ModelAndView gotoregister(){
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
