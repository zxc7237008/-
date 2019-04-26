package com.lovo.uploadsystem.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.lovo.uploadsystem.entity.JournalEntity;
import com.lovo.uploadsystem.entity.PageBean;
import com.lovo.uploadsystem.entity.RosterEntity;
import com.lovo.uploadsystem.service.IRosterService;
import com.lovo.uploadsystem.service.ImportService;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.IMarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RosterController {

   @Autowired
   private IRosterService rosterService;

   /***
    * 导入excel
    * @param request
    * @return
    */
   @RequestMapping("/importExcel")
   public String roster(HttpServletRequest request) throws Exception {

      MultipartHttpServletRequest multipartHttpServletRequest  = (MultipartHttpServletRequest) request;

      MultipartFile file = multipartHttpServletRequest.getFile("filename");

      if (file.isEmpty()){
         return "文件不能为空";
      }

      InputStream inputStream = null;
      try {
         inputStream = file.getInputStream();

         ImportParams params = new ImportParams();
         params.setTitleRows(1);
         params.setHeadRows(1);
         List<RosterEntity> list = ExcelImportUtil.importExcel(inputStream,RosterEntity.class,params);

         list.forEach(item->{
            //新增
            rosterService.insert(item);
         });

         return "redirect:showRosterListPage";

      }catch (Exception e){

         throw  new Exception("导入错误");

      }finally {
         try {
            inputStream.close();
         }catch (IOException e){
         }
      }

   }
   @RequestMapping("showRosterListPage")
   public ModelAndView showUserListPage(){
      ModelAndView modelAndView = new ModelAndView("roster");
      int pageNum = 0;
      int pageSize = 5;
      int pageAll = rosterService.getAllPage(pageSize);
      List<RosterEntity> list = rosterService.showRosterListPage(pageNum,pageSize);
      PageBean<RosterEntity> pageBean = new PageBean<>();
      pageBean.setList(list);
      pageBean.setPageNum(pageNum);
      pageBean.setPageAll(pageAll);
      modelAndView.addObject("pageBean",pageBean);
      return modelAndView;
   }


   @RequestMapping("rosterchangePage")
   @ResponseBody
   public PageBean changePage(int pageNum){
      int pageSize = 5;
      List<RosterEntity> list = rosterService.showRosterListPage(pageNum,pageSize);
      int pageAll = rosterService.getAllPage(pageSize);
      PageBean<RosterEntity> pageBean = new PageBean<>();
      pageBean.setList(list);
      pageBean.setPageAll(pageAll);
      pageBean.setPageNum(pageNum);
      return  pageBean;
   }
}
