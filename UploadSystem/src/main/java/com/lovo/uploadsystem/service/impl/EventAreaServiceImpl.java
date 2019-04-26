package com.lovo.uploadsystem.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lovo.uploadsystem.dao.IEventAreaDao;
import com.lovo.uploadsystem.entity.EventAreaEntity;
import com.lovo.uploadsystem.service.IEventAreaService;

@Service(value="eventAreaService")
public class EventAreaServiceImpl implements IEventAreaService {
	
	@Autowired
	private IEventAreaDao eventAreaDao;
	private final static String excel2003 =".xls";
    private final static String excel2010 =".xlsx";

	@Override
	public List<EventAreaEntity> getArea() {
		//将Iterable转成List
		List<EventAreaEntity> areaList = Lists.newArrayList(eventAreaDao.findAll());
		return areaList;
	}

	@Override
	public EventAreaEntity findArea(String areaId) {
		return eventAreaDao.findOne(areaId);
	}
	@Override
	public List<EventAreaEntity> findAllEventAreas() {
		
		return (List<EventAreaEntity>) eventAreaDao.findAll();
	}

	@Override
	public void saveEventArea(List<EventAreaEntity> listeventAreaEntity) {
		for(EventAreaEntity eventAreaEntity :listeventAreaEntity){
			eventAreaDao.save(eventAreaEntity);
		}
		
		
	}

	@Override
	public List<List<Object>> getresourceListByExcel(InputStream in, String fileName) throws Exception {
        List<List<Object>> list = null;

        //创建Excel工作薄

        Workbook work = this.getWorkbook(in,fileName);

        if(null == work){

            throw new Exception("创建Excel工作薄为空！");

        }

        Sheet sheet = null;

        Row row = null;

        Cell cell = null;



        list = new ArrayList<List<Object>>();

        for (int i = 0; i < work.getNumberOfSheets(); i++) {

            sheet = work.getSheetAt(i);

            if(sheet==null){continue;}



            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {

                row = sheet.getRow(j);

                if(row==null||row.getFirstCellNum()==j){continue;}



                List<Object> li = new ArrayList<Object>();

                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {

                    cell = row.getCell(y);

                    li.add(cell);

                }

                list.add(li);

            }

        }

        work.close();

        return list;
	}

	@Override
	public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {

        Workbook wb = null;

        String fileType = fileName.substring(fileName.lastIndexOf("."));

        if(excel2003.equals(fileType)){

            wb = new HSSFWorkbook(inStr);

        }else if(excel2010.equals(fileType)){

            wb = new XSSFWorkbook(inStr);

        }else{

            throw new Exception("解析的文件格式有误！");

        }

        return wb;
	}

	@Override
	public List<EventAreaEntity> showEventAreaListPage(int pageNum, int pageSize) {
		 PageRequest page = new PageRequest(pageNum,pageSize);
	        List<EventAreaEntity> list = eventAreaDao.showEvenAreaListPage(page);
	        return list;
	}

	@Override
	public List<EventAreaEntity> showEventAreaListPageto(String too, int pageNum, int pageSize) {
		  PageRequest page = new PageRequest(pageNum,pageSize);
		  List<EventAreaEntity> list = eventAreaDao.showEvenAreaListPageto(too,page);
		return list;
	}

	@Override
	public int getAllPage(int pageSize) {
		List<EventAreaEntity> list = (List<EventAreaEntity>) eventAreaDao.findAll();
        int alljournalSize = list.size();
        if (alljournalSize % pageSize == 0){
            alljournalSize = alljournalSize / pageSize;
        }else {
            alljournalSize = alljournalSize / pageSize + 1;
        }
        return alljournalSize;
	}

	@Override
	public int getAllPageto(String too, int pageSize) {
		List<EventAreaEntity> list = (List<EventAreaEntity>) eventAreaDao.getAllPageto(too);
        int alljournalSize = list.size();
        System.out.println("alljournalSize的值"+alljournalSize);
        if (alljournalSize % pageSize == 0){
            alljournalSize = alljournalSize / pageSize;
        }else {
            alljournalSize = alljournalSize / pageSize + 1;
        }
        return alljournalSize;
	}

	@Transactional
	public void delectEvenArea(String id) {
		eventAreaDao.delectEvenAreato(id);
		
	}
}
