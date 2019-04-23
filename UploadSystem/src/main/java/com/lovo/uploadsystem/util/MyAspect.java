package com.lovo.uploadsystem.util;

import java.text.DateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lovo.uploadsystem.entity.JournalEntity;
import com.lovo.uploadsystem.service.IJournalService;



@Component //通知spring来管理
@Aspect  //切面
public class MyAspect {
	
	@Autowired
	private IJournalService iJournalService;
	
	@Pointcut("execution(* com.lovo.statisticanalysis.controller.*.*(..))")
	public void mypoint(){};
	
	@Pointcut("execution(* com.lovo.statisticanalysis.controller.*.login*(..))")
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
    	if(methodName.contains("Continue")){
    		 incident = incident+"续报";
    	}if(methodName.contains("Event")){
   		 incident = incident+"上报";
    	}if(methodName.contains("shiftexchangecontent")){
      		 incident = incident+"上报日志";
       	}
    	
    
    }else if(methodName.contains("update")){
    	 incident = "修改";
    	if(methodName.contains("Event")){
  		 incident = incident+"上报";
    	}	
	}else if(methodName.contains("delete")){
		 incident = "删除";
	}else if(methodName.contains("shiftexchange")){
		 incident = "下班";
	}
    
    if(incident!=null&&!incident.equals("")){
    	 journalEntity.setIncident(incident);
    	    journalEntity.setName("duhao");
    	    iJournalService.savejournal(journalEntity);
    	     System.out.println(methodName+"我是AOP");
    }
    
    
   
	 }
	 
	 @AfterReturning(pointcut="register()",returning="returnVal")
	    public void afterReturn(JoinPoint joinPoint,Object returnVal){
		 
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
