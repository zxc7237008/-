package com.lovo.uploadsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lovo.uploadsystem.dao.IEventTypeDao;
import com.lovo.uploadsystem.entity.EventTypeEntity;
import com.lovo.uploadsystem.entity.PageBean;
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

	@Override
	public EventTypeEntity saveType(EventTypeEntity eventType) {
		return eventTypeDao.save(eventType);
		
	}

	@Override
	public PageBean<EventTypeEntity> findTypeByPage(int pageNum,int pageSize) {
		PageBean<EventTypeEntity> page = new PageBean<>();
		page.setPageNum(pageNum);
		
		//计算开始下标
		int startIndex = (pageNum-1)*pageSize;
		
		//获取总页数
		int n =(int)eventTypeDao.count();
		int pageAll = n/pageSize;
		page.setPageAll(pageAll);
		
		//按分页获取list
		List<EventTypeEntity> list = eventTypeDao.getTypeByPage(startIndex,pageSize);
		page.setList(list);
		return page;
	}
}
