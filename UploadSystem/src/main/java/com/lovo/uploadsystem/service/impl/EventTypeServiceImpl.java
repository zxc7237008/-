package com.lovo.uploadsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lovo.uploadsystem.dao.IEventTypeDao;
import com.lovo.uploadsystem.entity.EventTypeEntity;
import com.lovo.uploadsystem.service.IEventTypeService;



@Service("eventTypeService")
public class EventTypeServiceImpl implements IEventTypeService{
	@Autowired
	private IEventTypeDao eventTypeDao;
	
	public List<EventTypeEntity> findAllEventTypes(){
		return (List<EventTypeEntity>) eventTypeDao.findAll();
	}
	
	@Override
	public List<EventTypeEntity> getType() {
		//将Iterable转成List
		List<EventTypeEntity> typeList = Lists.newArrayList(eventTypeDao.findAll());
		return typeList;
	}

	@Override
	public EventTypeEntity findType(String typeId) {
		return eventTypeDao.findOne(typeId);
	}
}
