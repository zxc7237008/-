package com.lovo.uploadsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavigationController {
	//导航栏跳转主页面
	@RequestMapping("gotoMainPage")
	public String gotoMainPage() {
		
		return "home";
	}
	//导航栏跳转初报页面
	@RequestMapping("gotoFirstEventRep")
	public String gotoFirstEventRep() {
		
		return "first_report";
	}
	//导航栏跳转值班页面
	@RequestMapping("gotoRoster")
	public String gotoRoster() {
		
		return "roster";
	}
//	//导航栏跳转交班页面
//	@RequestMapping("gotoJournal")
//	public String gotoJournal() {
//		
//		return "journal";
//	}
	//导航栏跳转统计页面
	@RequestMapping("gotoChartPage")
	public String gotoChartPage() {
		
		return "chart";
	}
	//导航栏跳转用户管理页面
	@RequestMapping("gotoUserManage")
	public String gotoUserManage() {
		
		return "zhuce";
	}
	
}
