package com.lovo.uploadsystem.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.lovo.uploadsystem.entity.RosterEntity;
import com.lovo.uploadsystem.service.IFirstEventService;
import com.lovo.uploadsystem.service.ImportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportServiceImpl implements ImportService {

/*    @Override
    public List<RosterEntity> getBankListByExcel(InputStream inputStream,RosterEntity rosterEntity) throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);

        List<RosterEntity> list = ExcelImportUtil.importExcel(inputStream,rosterEntity,params);

        return  list;
    }*/

    @Override
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception{

        Workbook workbook = null;

        String fileType = fileName.substring(fileName.lastIndexOf("."));

        if (".xls".equals(fileType)){
            workbook = new HSSFWorkbook(inStr);
        }else if (".xlsx".equals(fileType)){
            workbook = new XSSFWorkbook(inStr);
        }else {
            throw  new Exception("请上传excel文件");
        }

        return workbook;
    }
}
