package com.lovo.uploadsystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lovo.uploadsystem.entity.PowerDto;

import com.lovo.uploadsystem.entity.RoleEntity;
import com.lovo.uploadsystem.util.JSONChange;





@Controller
public class RoleController {
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("savaRole")
	public ModelAndView savaRole(RoleEntity  role) throws JsonParseException, JsonMappingException, IOException{
		ModelAndView mv=new ModelAndView("role");
		String roleStr = JSON.toJSONString(role);
		RoleEntity u = restTemplate.getForEntity("http://SpringBootJ165/{roleStr}/1/savaRole",RoleEntity.class,roleStr).getBody();
		
		String listJson  = restTemplate.getForEntity("http://SpringBootJ165/findAllRole",String.class).getBody();
		System.out.println(listJson);
		List<RoleEntity> roleList = new ArrayList<>();
		roleList = (List<RoleEntity>) JSONChange.jsonToObj(roleList, listJson);
		mv.addObject("roleList", roleList);
		return mv;
        
	}
	@RequestMapping("gotosavaRole")
	public String gotosavaRole(){
		return "role";
	}
	
        
	}

