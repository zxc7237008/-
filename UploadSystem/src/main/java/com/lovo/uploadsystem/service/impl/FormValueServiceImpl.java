package com.lovo.uploadsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lovo.uploadsystem.dao.IFormValueDao;
import com.lovo.uploadsystem.entity.FormValueEntity;
import com.lovo.uploadsystem.service.IFormValueService;


@Service(value="formValueService")
public class FormValueServiceImpl implements IFormValueService {
	
	@Autowired
	private IFormValueDao formValueDao;

	@Override
	public void saveValue(FormValueEntity formValue) {
		formValueDao.save(formValue);

	}
	
	@Override
	public FormValueEntity findValue(String eventId) {
		return formValueDao.findValue(eventId);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public void delValue(String firstEventNo) {
		formValueDao.delValue(firstEventNo);
		
	}

}
