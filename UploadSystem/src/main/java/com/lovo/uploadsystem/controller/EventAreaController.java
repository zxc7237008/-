package com.lovo.uploadsystem.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lovo.uploadsystem.entity.EventAreaEntity;
import com.lovo.uploadsystem.entity.JournalEntity;
import com.lovo.uploadsystem.entity.PageBean;
import com.lovo.uploadsystem.service.IEventAreaService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 区域页面控制器
 * @author 文浩
 *
 */
@Controller
public class EventAreaController {
	
	@Autowired
	private IEventAreaService iEventAreaService;
	/**
	 * 访问地区页面
	 * @return
	 */
	@RequestMapping("eventArea")
	public ModelAndView eventArea(){
		ModelAndView modelAndView = new ModelAndView("event_area");
		 int pageNum = 0;
	        int pageSize = 5;
	        int pageAll = iEventAreaService.getAllPage(pageSize);
	        List<EventAreaEntity> list = iEventAreaService.showEventAreaListPage(pageNum,pageSize);
	        PageBean<EventAreaEntity> pageBean = new PageBean<>();
	        pageBean.setList(list);
	        pageBean.setPageNum(pageNum);
	        pageBean.setPageAll(pageAll);
	        modelAndView.addObject("pageBean",pageBean);
	        return modelAndView;
		
	}
	/**
	 * 删除地区
	 * @param request
	 * @return
	 */
	
	@RequestMapping("delectEventArea")
		public ModelAndView delectEventArea(HttpServletRequest request){
	ModelAndView modelAndView = new ModelAndView();
		 String id =(request.getParameter("id"));
//		 System.out.println(id);
		 iEventAreaService.delectEvenArea(id);
		 RedirectView rv = new RedirectView("eventArea");//重定向的视图
		 modelAndView.setView(rv);
			return modelAndView;
			
		}
	
	
	
	
	
	/**
	 * 分页需求方法
	 * @param pageNum
	 * @param too
	 * @return
	 */
	 	@RequestMapping("EventAreachangePage")
	    @ResponseBody
	    public PageBean changePage(int pageNum,String too){
	    	 List<EventAreaEntity> list=null;
	    	 int pageSize = 5;
	    	 PageBean<EventAreaEntity> pageBean = new PageBean<>();
	    	if(too!=null&&!too.equals("")){
	    		list = iEventAreaService.showEventAreaListPageto(too, pageNum, pageSize);
	    		 int pageAll = iEventAreaService.getAllPageto(too, pageSize);
	 	        pageBean.setList(list);
	 	        pageBean.setPageAll(pageAll);
	 	        pageBean.setPageNum(pageNum);
	    	}else{
	    		 list = iEventAreaService.showEventAreaListPage(pageNum,pageSize);
	    		 int pageAll = iEventAreaService.getAllPage(pageSize);
	 	        pageBean.setList(list);
	 	        pageBean.setPageAll(pageAll);
	 	        pageBean.setPageNum(pageNum);
			}
	       
	       return  pageBean;
	    }

	/**
     * 导入区域excel文件
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("uploadarea")
    @ResponseBody
    public   ModelAndView  uploadAreaExcel(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        InputStream inputStream =null;
        List<List<Object>> list = null;
        System.out.println("进入到uploadAreaExcel方法中");
        MultipartFile file = multipartRequest.getFile("filename");
        if(file.isEmpty()){
            String massge = "添加失败,文件不能为空";
            ModelAndView mv=new ModelAndView();
            RedirectView rv = new RedirectView("eventArea");//重定向的视图
    		mv.setView(rv);
            mv.addObject("massge",massge);
            return mv;
        }
        inputStream = file.getInputStream();
        list = iEventAreaService.getresourceListByExcel(inputStream,file.getOriginalFilename());
        inputStream.close();
        //代码健壮性  这里验证操作
        //通过mv.addObject  键值对   发送相对应的信息到页面   页面通过   ${键}  获取到值
        List<Object> objectList = list.get(1);
        if(objectList.size()!=2){
            String massge = "添加失败，excel文件格式不正确";
            ModelAndView mv=new ModelAndView();
            RedirectView rv = new RedirectView("eventArea");//重定向的视图
    		mv.setView(rv);
            mv.addObject("massge",massge);
            return mv;
        }else {
            //连接数据库部分  循环遍历出值  添加到实体对象中  调用业务层  添加到数据库
            List<EventAreaEntity> arealist = new ArrayList<EventAreaEntity>();
            for (int i = 0; i < list.size(); i++) {
            	EventAreaEntity areaEntity = new EventAreaEntity();
                List<Object> lo = list.get(i);
                areaEntity.setAreaName(lo.get(1).toString());
                arealist.add(areaEntity);
            }
            System.out.println("已经出循环了"+arealist);
            iEventAreaService.saveEventArea(arealist);
            String massge = "添加成功";
            ModelAndView mv=new ModelAndView();
            RedirectView rv = new RedirectView("eventArea");//重定向的视图
    		mv.setView(rv);
            mv.addObject("massge",massge);
            return mv;
        }
    }
	

}
