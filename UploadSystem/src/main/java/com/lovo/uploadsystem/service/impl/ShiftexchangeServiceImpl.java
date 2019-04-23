package com.lovo.uploadsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

}
