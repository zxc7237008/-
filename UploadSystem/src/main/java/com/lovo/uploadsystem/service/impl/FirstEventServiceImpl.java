package com.lovo.uploadsystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lovo.uploadsystem.dao.IFirstEventDao;
import com.lovo.uploadsystem.entity.FirstEventAreaEventTypeDTO;
import com.lovo.uploadsystem.entity.FirstEventEntity;
import com.lovo.uploadsystem.entity.StateEntity;
import com.lovo.uploadsystem.service.IFirstEventService;
import com.lovo.uploadsystem.util.infoDTO;


@Service("firstEventService")
public class FirstEventServiceImpl implements IFirstEventService{
	
	@Autowired
	private IFirstEventDao firstEventDao;

	
	@Override
	public void saveEvent(FirstEventEntity event) {
		firstEventDao.save(event);
		
	}
	
	@Override
	public List<FirstEventEntity> findFirstEventByEventState(int eventState) {
		
		return firstEventDao.findFirstEventByEventState(eventState);
	}

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
	public int getFirstEventAllPages(String typeName,String eventLevelStr,String areaName,String eventStateStr,int pageSize) {
		
		if(typeName.equals("事件类型")) {
			typeName = "";
		}
		if(eventLevelStr.equals("事件等级")) {
			eventLevelStr = "";
		}
		if(areaName.equals("区域")) {
			areaName = "";
		}
		if(eventStateStr.equals("事件状态")) {
			eventStateStr = "";
		}
		
		int eventState = 0;	
		if(eventStateStr != null && eventStateStr != "") {
			switch (eventStateStr) {
			case "未处理": eventState = 1;
				break;
			case "处理中": eventState = 2;
				break;
			case "结束": eventState = 3;
				break;
			}
		}
		String eventLevel = null;
		if(eventLevelStr != null && eventLevelStr != "") {
			switch (eventLevelStr) {
			case "一级事件": eventLevel = "1";
				break;
			case "二级事件": eventLevel = "2";
				break;
			case "三级事件": eventLevel = "3";
				break;
			}
		}
		
		List<FirstEventEntity> list = (List<FirstEventEntity>) firstEventDao.findAllEventByState(typeName, eventLevel, areaName, eventState);
		 int firstEventSize = list.size();
	     if (firstEventSize % pageSize == 0){
	    	 firstEventSize = firstEventSize / pageSize;
        }else {
        	firstEventSize = firstEventSize / pageSize + 1;
        }
		
		return firstEventSize;
	}

	@Override
	public List<FirstEventAreaEventTypeDTO> findAllFirstEventsByPage(String typeName, String eventLevelStr,
			String areaName, String eventStateStr, int pageNum, int pageSize) {
		
		int index = pageNum*pageSize;

		if(typeName.equals("事件类型")) {
			typeName = "";
		}
		if(eventLevelStr.equals("事件等级")) {
			eventLevelStr = "";
		}
		if(areaName.equals("区域")) {
			areaName = "";
		}
		if(eventStateStr.equals("事件状态")) {
			eventStateStr = "";
		}
		
		int eventState = 0;	
		if(eventStateStr != null && eventStateStr != "") {
			switch (eventStateStr) {
			case "未处理": eventState = 1;
				break;
			case "处理中": eventState = 2;
				break;
			case "结束": eventState = 3;
				break;
			}
		}
		String eventLevel = null;
		if(eventLevelStr != null && eventLevelStr != "") {
			switch (eventLevelStr) {
			case "一级事件": eventLevel = "1";
				break;
			case "二级事件": eventLevel = "2";
				break;
			case "三级事件": eventLevel = "3";
				break;
			}
		}
		
		List<FirstEventEntity> list = firstEventDao.findAllFirstEventsByPage(typeName, eventLevel, areaName, eventState, index, pageSize);
		List<FirstEventAreaEventTypeDTO> dtoList = infoDTO.getDtoList(list);
		return dtoList;
	}

	
}
