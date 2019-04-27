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
import com.lovo.uploadsystem.entity.PowerDtoReslut;
import com.lovo.uploadsystem.entity.PowerEntity;
import com.lovo.uploadsystem.entity.RoleEntity;
import com.lovo.uploadsystem.util.JSONChange;





@Controller
public class PowerController {
	@Autowired
	RestTemplate restTemplate;
	
	
	@RequestMapping("savaPower")
	public ModelAndView savaPower(PowerEntity  power) throws JsonParseException, JsonMappingException, IOException{
		ModelAndView mv=new ModelAndView("power");
		String powerStr = JSON.toJSONString(power);
		PowerEntity  u = restTemplate.getForEntity("http://SpringBootJ165/{powerStr}/1/savaPower",PowerEntity.class,powerStr).getBody();
		
		String listJson  = restTemplate.getForEntity("http://SpringBootJ165/findAllPower",String.class).getBody();
		
		List<PowerEntity> listPower = new ArrayList<>();
		listPower = (List<PowerEntity>) JSONChange.jsonToObj(listPower, listJson);
		mv.addObject("listPower", listPower);
		return mv;
	}
	@RequestMapping("gotosavaPower")
	public String gotosavaPower(){
		return "power";
	}
	
	
        
	}
