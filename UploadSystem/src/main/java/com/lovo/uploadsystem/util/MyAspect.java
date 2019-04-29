package com.lovo.uploadsystem.util;

import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lovo.uploadsystem.entity.JournalEntity;
import com.lovo.uploadsystem.entity.UserEntity;
import com.lovo.uploadsystem.service.IJournalService;

/**
 * 文浩
 * @author 文浩
 *
 */

@Component //通知spring来管理
@Aspect  //切面
public class MyAspect {
	
	@Autowired
	private IJournalService iJournalService;
	
	@Pointcut("execution(* com.lovo.uploadsystem.controller.*.*(..))")
	public void mypoint(){};
	
	@Pointcut("execution(* com.lovo.uploadsystem.controller.*.login*(..))")
	public void register(){};
	
	 @Before("mypoint()")
	 public void message(JoinPoint join){

    String methodName=	join.getSignature().getName();
    Date now = new Date(); 
    DateFormat d4 = DateFormat.getInstance();
    String str4 = d4.format(now);
    String incident = null;
    System.out.println(str4);
	JournalEntity  journalEntity = new JournalEntity();
	journalEntity.setData(str4);
	//添加方法
    if(methodName.contains("save")){
    	 incident = "添加";
    	if(methodName.contains("Continue")){//续报
    		 incident = incident+"续报";
    	}if(methodName.contains("Event")){//上报
   		 incident = incident+"上报";
    	}if(methodName.contains("shiftexchangecontent")){//日志
      		 incident = incident+"上报日志";
       	}
    	
    
    }else if(methodName.contains("update")){
    	 incident = "修改";
    	if(methodName.contains("Event")){
  		 incident = incident+"上报";
    	}	
	}else if(methodName.contains("delete")){
		 incident = "删除";
		 if(methodName.contains("Event")){
	  		 incident = incident+"上报";
	    	}	
	}else if(methodName.contains("shiftexchange")){
		 incident = "下班了";
	}else if(methodName.contains("login")){
		 incident = "登录";
	}
    
    if(incident!=null&&!incident.equals("")){
    	//获取session
    	  HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	   HttpSession session =request.getSession(); 
    	   UserEntity user =  (UserEntity) session.getAttribute("User");
    	   System.out.println("aop"+user.getUname()+"22222222222222");//占时不知道名字
    	   //把对象放入数据库以及保存在本地txt文档里
    	   journalEntity.setIncident(incident);
    	   
    	   
    	   //需要删除的代码
    	    journalEntity.setName("duhao");//修改的difan
    	    //需要删除的代码结尾
    	    
    	    
    	    //需要放行的代码
//    	    journalEntity.setName(user.getUname());//修改的difan
    	    //把对象保存在数据库中
    	    iJournalService.savejournal(journalEntity);
    	    //保存在本地Txt文档里
    	    WrtieUtil.writeFile(journalEntity);
    	     
    }
    
    
   
	 }
	 //如果登录不放入session  就在这里获取
	 @AfterReturning(pointcut="register()",returning="returnVal")
	    public void afterReturn(JoinPoint joinPoint,Object returnVal){
		 //获取session
   	  HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
   	   HttpSession session =request.getSession(); 
   	   UserEntity user =  (UserEntity) session.getAttribute("userentity");
   	   System.out.println(user.getUname());//占时不知道名字
		 
		 
	        System.out.println("AOP 返回值:" + returnVal);
	       
	    }
	 
	 @Around("mypoint()")
	   public Object rmessage(ProceedingJoinPoint join){
		   System.out.println("执行方法前");
		   Object obj=null;
		   try {
		obj=join.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}//调用目标方法
		   System.out.println("执行方法后");
		   return obj;
	   }
}

