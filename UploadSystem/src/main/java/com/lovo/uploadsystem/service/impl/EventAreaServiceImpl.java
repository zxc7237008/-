package com.lovo.uploadsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lovo.uploadsystem.dao.IEventAreaDao;
import com.lovo.uploadsystem.entity.EventAreaEntity;
import com.lovo.uploadsystem.service.IEventAreaService;

@Service(value="eventAreaService")
public class EventAreaServiceImpl implements IEventAreaService {
	
	@Autowired
	private IEventAreaDao eventAreaDao;

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

}
