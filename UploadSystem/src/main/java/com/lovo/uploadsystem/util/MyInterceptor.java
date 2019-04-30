package com.lovo.uploadsystem.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lovo.uploadsystem.dto.PowerDto;

public class MyInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean bl=false;
        //从session中获取权限对象
        List<PowerDto> list = (List<PowerDto>) request.getSession().getAttribute("userList");
        if(null == list){
            //跳到登录页面
            response.sendRedirect("/gotoLogin");

        }else {
            //获取访问的URI
            String uri = request.getServletPath();
            if(uri.equals("/error")) {
            	response.sendRedirect("/500");
            	return false;
            }
            for (PowerDto p : list) {
            	if(p.getPowerUri().equals("/admin")) {
            		return true;
            	}
                if(uri.contains(p.getPowerUri())){
                    bl=true;//找到了访问的uri
                } 
            }
            if(!bl) {
                //跳到错误页面
            	response.sendRedirect("/unauthorized");
	            
            }
        	bl = true;
        }

        //判断URI是否权限对象中存在

        return bl;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
