package com.lovo.uploadsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lovo.uploadsystem.dao.IShiftexchangeDao;
import com.lovo.uploadsystem.entity.ShiftexchangeEntity;
import com.lovo.uploadsystem.service.IShiftexchangeService;

@Service(value="shiftexchangeService")
public class ShiftexchangeServiceImpl implements IShiftexchangeService{

	@Autowired
	private IShiftexchangeDao iShiftexchangeDao;
	@Override
	public void savejournal(ShiftexchangeEntity shiftexchangeEntity) {
		iShiftexchangeDao.save(shiftexchangeEntity);
		
	}
	@Override
	public List<ShiftexchangeEntity> showshiftexListPage(int pageNum, int pageSize) {
		PageRequest page = new PageRequest(pageNum,pageSize);
        List<ShiftexchangeEntity> list = iShiftexchangeDao.showshiftexListPage(page);
        return list;
	}
	@Override
	public List<ShiftexchangeEntity> showshiftexListPageto(String too, int pageNum, int pageSize) {
		  PageRequest page = new PageRequest(pageNum,pageSize);
		  List<ShiftexchangeEntity> list = iShiftexchangeDao.showshiftexListPageto(too,page);
		return list;
	}
	@Override
	public int getAllPage(int pageSize) {
		List<ShiftexchangeEntity> list = (List<ShiftexchangeEntity>) iShiftexchangeDao.findAll();
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
		List<ShiftexchangeEntity> list = (List<ShiftexchangeEntity>) iShiftexchangeDao.getAllPageto(too);
        int alljournalSize = list.size();
        System.out.println("alljournalSize的值"+alljournalSize);
        if (alljournalSize % pageSize == 0){
            alljournalSize = alljournalSize / pageSize;
        }else {
            alljournalSize = alljournalSize / pageSize + 1;
        }
        return alljournalSize;
	}

}
