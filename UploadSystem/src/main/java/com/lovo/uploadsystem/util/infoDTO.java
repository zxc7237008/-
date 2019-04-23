package com.lovo.uploadsystem.util;

import java.util.ArrayList;
import java.util.List;

import com.lovo.uploadsystem.entity.FirstEventAreaEventTypeDTO;
import com.lovo.uploadsystem.entity.FirstEventEntity;



public class infoDTO {
	
	public static List<FirstEventAreaEventTypeDTO> getDtoList(List<FirstEventEntity> list){
		List<FirstEventAreaEventTypeDTO> dtoList = new ArrayList<>();
		for (FirstEventEntity FirstEvent : list) {
			String resultEventState = "";
			
			if(FirstEvent.getEventState() != 0) {
				switch (FirstEvent.getEventState()) {
				case 1: resultEventState = "未处理";
					break;
				case 2: resultEventState = "处理中";
					break;
				case 3: resultEventState =  "结束";
					break;
				}
			}
			String resultEventLevel = "";
			if(FirstEvent.getEventLevel() != null && FirstEvent.getEventLevel() != "") {
				switch (FirstEvent.getEventLevel()) {
				case "1": resultEventLevel = "一级事件";
					break;
				case "2": resultEventLevel = "二级事件";
					break;
				case "3": resultEventLevel = "三级事件";
					break;
				}
			}
			FirstEventAreaEventTypeDTO dto = new FirstEventAreaEventTypeDTO(FirstEvent.getEventName(),FirstEvent.getEventType().getTypeName() ,
					resultEventLevel,FirstEvent.getEventArea().getAreaName(),FirstEvent.getDiscoverer(), FirstEvent.getDiscovererTel(),
					resultEventState);
			dtoList.add(dto);
		}
		return dtoList;
		
	}
	
}
