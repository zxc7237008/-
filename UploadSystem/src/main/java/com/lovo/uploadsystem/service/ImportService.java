package com.lovo.uploadsystem.service;

import com.lovo.uploadsystem.entity.RosterEntity;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.List;

/***
 * excel 处理类
 */
public interface ImportService {

/*    *//***
     * excel 上传处理
     * @param
     * @param fileName
     * @return
     *//*
     List<RosterEntity> getBankListByExcel(String fileName) throws Exception;*/


    /***
     * @description 判断上传格式
     * @param inStr
     * @param fileName
     * @return
     */
     Workbook getWorkbook(InputStream inStr,String fileName) throws Exception;
}
