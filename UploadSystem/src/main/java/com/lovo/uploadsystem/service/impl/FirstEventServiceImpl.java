package com.lovo.uploadsystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lovo.uploadsystem.dao.IFirstEventDao;
import com.lovo.uploadsystem.entity.FirstEventEntity;
import com.lovo.uploadsystem.entity.StateEntity;
import com.lovo.uploadsystem.service.IFirstEventService;


@Service("firstEventService")
public class FirstEventServiceImpl implements IFirstEventService{
	
	@Autowired
	private IFirstEventDao firstEventDao;

	
	public List<FirstEventEntity> findFirstEventByEventState(int eventState) {
		
		return firstEventDao.findFirstEventByEventState(eventState);
	}


	@Override
	public void saveEvent(FirstEventEntity event) {
		firstEventDao.save(event);
		
	}

	
//	public List<FirstEventEntity> findFirstEventslikeEventTypeEventLevelEventArea(String typeName,
//			String eventLevel, String areaName) {
//		
//		return firstEventDao.findFirstEventslikeEventTypeEventLevelEventArea(typeName, eventLevel, areaName);
//	}
	
	@Override
	public List<StateEntity> findAllFirstEventStates() {
		List<FirstEventEntity> list1 = findFirstEventByEventState(1);
		List<FirstEventEntity> list2 = findFirstEventByEventState(2);
		List<FirstEventEntity> list3 = findFirstEventByEventState(3);
		
		List<StateEntity> strList = new ArrayList<>();
			
			
		if (list1 != null && list1.size() != 0) {
			StateEntity s1 = new StateEntity();
			s1.setEventState("未处理");
			strList.add(s1);
		}if (list2 != null && list2.size() != 0) {
			StateEntity s2 = new StateEntity();
			s2.setEventState("处理中");
			strList.add(s2);
		}if(list3 != null && list3.size() != 0){
			StateEntity s3 = new StateEntity();
			s3.setEventState("结束");
			strList.add(s3);
		}
		return strList;
		
	}


	@Override
	public List<FirstEventEntity> findFirstEventsByEventTypeEventLevelEventAreaState(String typeName, String eventLevel,
			String areaName, String eventStateStr) {
		int eventState = 0;	
		
		switch (eventStateStr) {
		case "未处理": eventState = 1;
			break;
		case "处理中": eventState = 2;
			break;
		case "结束": eventState = 3;
			break;
		}
		
		return firstEventDao.findFirstEventsByEventTypeEventLevelEventAreaState(typeName, eventLevel, areaName, eventState);
	}

	
	
	
	
}
