package com.lovo.uploadsystem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lovo.uploadsystem.dto.RolePowerDto;
import com.lovo.uploadsystem.dto.UserRoleDto;
import com.lovo.uploadsystem.entity.PowerEntity;
import com.lovo.uploadsystem.entity.UserEntity;
import com.lovo.uploadsystem.util.JSONChange;

@Controller
public class RolePowerContrlloer {
	@Autowired
	RestTemplate restTemplate;
	
	
	
		//跳转到角色权限维护界面
			@RequestMapping("gotoRolePower")
			public ModelAndView gotoRolePower(String roleName,String roleId) throws JsonParseException, JsonMappingException, IOException{
				ModelAndView mv=new ModelAndView("role_power");
				String  listJson = restTemplate.getForEntity("http://SpringBootJ165/{roleName}/{roleId}/gotoRolePower",String.class,roleName,roleId).getBody();
				RolePowerDto dto = new RolePowerDto();	
				dto =  (RolePowerDto) JSONChange.jsonToObj(dto, listJson);
				
				//该角色拥有的权限
				 List<PowerEntity> listRolePower=	dto.getListRolePower();
				//该角色不拥有的权限
				 List<PowerEntity> noListRolePower   =dto.getNoListRolePower();
				 String roleId1  =  dto.getRoleId();
				 String  roleName1  = dto.getRoleName();
				 mv.addObject("roleName", roleName1);
					mv.addObject("roleId", roleId1);
					mv.addObject("listRolePower", listRolePower);
					mv.addObject("noListRolePower", noListRolePower);
				 
				return mv;
			}
	
}
