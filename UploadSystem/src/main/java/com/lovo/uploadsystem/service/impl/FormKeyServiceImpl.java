package com.lovo.uploadsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovo.uploadsystem.dao.IFormKeyDao;
import com.lovo.uploadsystem.entity.FormKeyEntity;
import com.lovo.uploadsystem.service.IFormKeyService;

@Service(value="formKeyService")
public class FormKeyServiceImpl implements IFormKeyService {
	
	@Autowired
	private IFormKeyDao formKeyDao;

	@Override
	public void saveKey(FormKeyEntity key) {
		formKeyDao.save(key);
	}

}
