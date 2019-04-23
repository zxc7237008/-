package com.lovo.uploadsystem.service;

import java.util.List;

import com.lovo.uploadsystem.entity.EventAreaEntity;


public interface IEventAreaService {

	public List<EventAreaEntity> getArea();
	
	public EventAreaEntity findArea(String areaId);
	
	/**
	 * 查询所有地区信息
	 * @return 地区信息集合
	 */
	public List<EventAreaEntity> findAllEventAreas();
}
