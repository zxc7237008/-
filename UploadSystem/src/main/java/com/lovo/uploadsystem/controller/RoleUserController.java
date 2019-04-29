package com.lovo.uploadsystem.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lovo.uploadsystem.dto.UserRoleDto;
import com.lovo.uploadsystem.entity.PowerEntity;
import com.lovo.uploadsystem.entity.RoleEntity;
import com.lovo.uploadsystem.entity.UserEntity;
import com.lovo.uploadsystem.entity.UserRoleEntity;
import com.lovo.uploadsystem.util.JSONChange;





@Controller
public class RoleUserController {

   @Autowired
	RestTemplate restTemplate;
	//跳转到角色用户维护界面
		@RequestMapping("gotoRoleUser")
		public ModelAndView gotoRoleUser(String roleName,String roleId) throws JsonParseException, JsonMappingException, IOException{
			ModelAndView mv=new ModelAndView("user_role");
			String  listJson = restTemplate.getForEntity("http://SpringBootJ165/{roleName}/{roleId}/gotoRoleUser",String.class,roleName,roleId).getBody();
			UserRoleDto dto = new UserRoleDto();	
			dto =  (UserRoleDto) JSONChange.jsonToObj(dto, listJson);
			
			 List<UserEntity> listUser= dto.getListUser();
			 List<UserEntity> nolistUser   =dto.getNolistUser();
			 String roleId1  =  dto.getRoleId();
			 String  roleName1  = dto.getRoleName();
			 mv.addObject("roleName", roleName1);
				mv.addObject("roleId", roleId1);
				mv.addObject("listUser", listUser);
				mv.addObject("nolistUser", nolistUser);
			 
			return mv;
		}
		

		
		@RequestMapping("updateRoleUser")
		public ModelAndView  updateRoleUser(String roleName,String roleId,String addName,String delName) throws JsonParseException, JsonMappingException, IOException{
			if(addName == null || "".equals(addName)) {
				addName = "0";
			}
			if(delName == null || "".equals(delName)) {
				delName = "0";
			}
			//重新查询
			ModelAndView mv=new ModelAndView("user_role");
			String  listJson = restTemplate.getForEntity("http://SpringBootJ165/{roleName}/{roleId}/{addName}/{delName}/updateRoleUser",String.class,roleName,roleId,addName,delName).getBody();
			UserRoleDto dto = new UserRoleDto();	
			dto =  (UserRoleDto) JSONChange.jsonToObj(dto, listJson);
			
			 List<UserEntity> listUser= dto.getListUser();
			 List<UserEntity> nolistUser   =dto.getNolistUser();
			
			 String roleId1  =  dto.getRoleId();
			 String  roleName1  = dto.getRoleName();
			 mv.addObject("roleName", roleName1);
				mv.addObject("roleId", roleId1);
				mv.addObject("listUser", listUser);
				mv.addObject("nolistUser", nolistUser);
			 
			return mv;
		}
	
		
		@RequestMapping("delRole")
		public ModelAndView delRole(String roleId) {
			
			RedirectView rv=new RedirectView("gotosavaRole");
			ModelAndView mv=new ModelAndView();
			mv.setView(rv);
			 restTemplate.getForEntity("http://SpringBootJ165/{roleId}/delRole",String.class,roleId).getBody();	
			
			return mv;
			
		}
	
		
		
}
