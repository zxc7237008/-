package com.lovo.uploadsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lovo.uploadsystem.entity.JournalEntity;
import com.lovo.uploadsystem.entity.PageBean;
import com.netflix.client.http.HttpRequest;
import com.sun.research.ws.wadl.Request;

@Controller
public class ShiftexchangeController {

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
		if(password.equals("wenhao")){
         modelAndView = new ModelAndView("*");
		}else{
			modelAndView = new ModelAndView("*");
		}
        return modelAndView;
    }
	
	@RequestMapping("saveshiftexchangecontent")
    public ModelAndView saveshiftexchangecontent(String daily){
		 ModelAndView modelAndView=new ModelAndView();
		System.out.println(daily);
		if(daily.equals("wenhao")){
			RedirectView rv = new RedirectView("showjournalListPage");//重定向的视图
			modelAndView.setView(rv);
		}else{
			RedirectView rv = new RedirectView("showjournalListPage");//重定向的视图
			modelAndView.setView(rv);
		}
        return modelAndView;
    }
	
}
