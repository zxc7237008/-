package com.lovo.uploadsystem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSON;
import com.lovo.uploadsystem.dto.PowerDto;
import com.lovo.uploadsystem.dto.PowerDtoReslut;
import com.lovo.uploadsystem.entity.UserEntity;
import com.lovo.uploadsystem.util.StringUtil;




@Controller
public class UserController {
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("login")
	public ModelAndView login(String userName,String password,HttpSession session){
		ModelAndView mv=new ModelAndView();
		RedirectView rv = new RedirectView("/home");
		mv.setView(rv);
		PowerDtoReslut  pdresult =	restTemplate.getForEntity("http://SpringBootJ165/{userName}/{password}/1/PowerDtoReslut", PowerDtoReslut.class,userName,password).getBody();

		List<PowerDto> list=pdresult.getDto();
		UserEntity user = pdresult.getUser();
		if(null==list){
			//用户名或密码错误
			mv.addObject("loginMessage", StringUtil.loginMessage);
			mv.setViewName("login");
		}else{
			//登录成功就把用户信息放入到session
			session.setAttribute("userList", list);
			session.setAttribute("user", user);
		}
		//远程调用用户权限
		return mv;
	}
	@RequestMapping("gotoLogin")
	public String gotoLogin(){
		return "login";
	}
	
	@RequestMapping("zhuce")
	public ModelAndView zhuce(UserEntity  user){
		ModelAndView mv=new ModelAndView("home");
		String userStr = JSON.toJSONString(user);
		UserEntity u = restTemplate.getForEntity("http://SpringBootJ165/{userStr}/1/zhuce",UserEntity.class,userStr).getBody();
		return mv;
         
	}
	@RequestMapping("gotozhuce")
	public String gotozhuce(){
		return "zhuce";
	}
	
	@ResponseBody
	@RequestMapping("getUserList")
	public List<PowerDto> getUserList(@SessionAttribute List<PowerDto> userList){
		
		return userList;
	}
	
	@RequestMapping("unauthorized")
	public String unauthorized(){
		return "unauthorized";
	}
	
	@RequestMapping("500")
	public String error(){
		return "error";
	}
	
	@RequestMapping("userInfo")
	public String userInfo() {
		return "userInfo";
	}
	
}

