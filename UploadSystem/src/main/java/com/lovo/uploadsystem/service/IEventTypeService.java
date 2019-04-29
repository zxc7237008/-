package com.lovo.uploadsystem.service;

import java.util.List;

import com.lovo.uploadsystem.entity.EventTypeEntity;
import com.lovo.uploadsystem.entity.PageBean;


public interface IEventTypeService {
	
	/**
	 * 获取所有的事件类型
	 * @return 事件类型实体集合
	 */
	public List<EventTypeEntity> getType();
	
	/**
	 * 通过id获取指定的事件类型实体
	 * @param typeId 事件类型id
	 * @return	事件类型实体
	 */
	public EventTypeEntity findType(String typeId);
	
	/**
	 * 查询所有的事件类型
	 * @return 事件类型集合
	 */
	public List<EventTypeEntity> findAllEventTypes();

	/**
	 * 保存事件类型
	 * @param eventType 事件类型实体
	 */
	public EventTypeEntity saveType(EventTypeEntity eventType);

	/**
	 * 分页查找事件类型
	 * @param pageNum
	 * @return
	 */
	public PageBean<EventTypeEntity> findTypeByPage(int pageNum,int pageSize);
	
}
