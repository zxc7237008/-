package com.lovo.uploadsystem.util;

import java.util.ArrayList;
import java.util.List;

import com.lovo.uploadsystem.dto.NoDealWithDto;
import com.lovo.uploadsystem.entity.FirstEventEntity;
import com.lovo.uploadsystem.entity.FormKeyEntity;
import com.lovo.uploadsystem.entity.FormValueEntity;

public class FirstEvent2DtoUtil {
	
	public static NoDealWithDto firstEvent2Dto(FirstEventEntity event,FormKeyEntity keys, FormValueEntity values) {
		NoDealWithDto dto = new NoDealWithDto();
		dto.setEventId(event.getFirstEventNo());
		dto.setEventName(event.getEventName());
		dto.setEventType(event.getEventType().getTypeName());
		dto.setEventLevel(event.getEventLevel());
		dto.setEventArea(event.getEventArea().getAreaName());
		dto.setHurtPopulation(event.getCasualty());
		dto.setAlarmPerson(event.getDiscoverer());
		dto.setAlarmTel(event.getDiscovererTel());
		dto.setAlarmAddress(event.getDetailAddr());
		dto.setEventUploadPeople(event.getReporter());
		dto.setEventTime(event.getEventDatetime());
		
		
		String[] keyStr = new String[6];
		String[] valueStr = new String[6];
		
		keyStr[0] = keys.getKey1();
		keyStr[1] = keys.getKey2();
		keyStr[2] = keys.getKey3();
		keyStr[3] = keys.getKey4();
		keyStr[4] = keys.getKey5();
		keyStr[5] = keys.getKey6();
		
		valueStr[0] = values.getValue1();
		valueStr[1] = values.getValue2();
		valueStr[2] = values.getValue3();
		valueStr[3] = values.getValue4();
		valueStr[4] = values.getValue5();
		valueStr[5] = values.getValue6();
		
		StringBuffer uniqueAttrBuffer =new StringBuffer("{");
		for(int i=0; i < 6; i++) {
			if(keyStr[i] == null) {
				uniqueAttrBuffer.deleteCharAt(uniqueAttrBuffer.length() - 1);
				uniqueAttrBuffer.append("}");
				break;
			}
			uniqueAttrBuffer.append(keyStr[i]);
			uniqueAttrBuffer.append(":");
			uniqueAttrBuffer.append(valueStr[i]);
			uniqueAttrBuffer.append(",");
			
		}
		String uniqueAttr = new String(uniqueAttrBuffer);
		dto.setUniqueAttr(uniqueAttr);
		return dto;
		
	}

}
