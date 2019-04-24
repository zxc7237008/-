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
	 * 查询所有事件状态
	 * @return 事件状态集合
	 */
	public List<StateEntity> findAllFirstEventStates();
	
	/**
	  * 得到所有事件按照pageSize大小分配的总页数
	 * @param typeName 事件名称
	 * @param eventLevel 事件等级
	 * @param areaName 地区名称
	 * @param eventState 事件状态
	 * @param pageSize 每页的条数
	 * @return 分的页数
	 */
	public int getFirstEventAllPages(String typeName,String eventLevel,String areaName,String eventState,int pageSize);
	
	/**
	 * 根据条件查询事件，并分页
	 * @param typeName 事件名称
	 * @param eventLevel 事件等级
	 * @param areaName 地区名称
	 * @param eventState 事件状态
	 * @param pageNum 页码起始位置
	 * @param pageSize 每页的条数
	 * @return 事件的集合
	 */
	public List<FirstEventAreaEventTypeDTO> findAllFirstEventsByPage(String typeName,String eventLevel,String areaName,String eventState,int pageNum, int pageSize);

}
