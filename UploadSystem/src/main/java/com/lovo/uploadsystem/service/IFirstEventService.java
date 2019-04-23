package com.lovo.uploadsystem.service;

import java.util.List;

import com.lovo.uploadsystem.entity.FirstEventAreaEventTypeDTO;
import com.lovo.uploadsystem.entity.FirstEventEntity;
import com.lovo.uploadsystem.entity.StateEntity;



public interface IFirstEventService {
	
	/**
	  *  通过事件状态查找对应状态事件的信息
	 * @param eventState 1---未上报  2---以上报处理中  3---事件结束
	 * @return 对应状态事件信息的集合
	 */
	public List<FirstEventEntity> findFirstEventByEventState(int eventState);

	/**
	 * 保存事件
	 * @param event
	 */
	public void saveEvent(FirstEventEntity event);

	
	/**
	 * 主页面模糊查询事件信息
	 * @param typeName 类型名称
	 * @param eventLevel 事件等级
	 * @param areaName 地区名称
	 * @param eventState 事件状态
	 * @return 事件集合
	 */
	public List<FirstEventAreaEventTypeDTO> findFirstEventsByEventTypeEventLevelEventAreaState(String typeName,String eventLevel,String areaName,String eventState);
	
	/**
	 * 查询所有事件状态
	 * @return 事件状态集合
	 */
	public List<StateEntity> findAllFirstEventStates();
	
	/**
	 * 查询所有的事件信息
	 * @return 事件集合
	 */
	public List<FirstEventAreaEventTypeDTO> findAllFirstEvents(int eventState);
}
