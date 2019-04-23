package com.lovo.uploadsystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lovo.uploadsystem.entity.FirstEventEntity;


public interface IFirstEventDao extends CrudRepository<FirstEventEntity, String>{
	
	/**
	  *  通过事件状态查找对应状态事件的信息
	 * @param eventState 1---未上报  2---以上报处理中  3---事件结束
	 * @return 对应状态事件信息的集合
	 */
	@Query(value="SELECT * from t_first_event WHERE event_state=?1",nativeQuery=true)
	public List<FirstEventEntity> findFirstEventByEventState(int eventState);
	
	/**
	 * 通过事件名称、事件等级、地区名称模糊查询相关的事件信息
	 * @param typeName 事件名称
	 * @param eventLevel 事件等级
	 * @param areaName 地区名称
	 * @return 相关的事件集合
	 */
//	@Query(value="",nativeQuery=true)
//	public List<FirstEventEntity> findFirstEventslikeEventTypeEventLevelEventArea(String typeName,String eventLevel,String areaName);
	
	/**
	 * 主页面模糊查询事件信息
	 * @param typeName 类型名称
	 * @param eventLevel 事件等级
	 * @param areaName 地区名称
	 * @param eventState 事件状态
	 * @return 事件集合
	 */
	@Query(value="SELECT fe.event_name,et.type_name,fe.event_level,ea.area_name,fe.discoverer,fe.discoverer_tel,fe.event_state " + 
			" from t_first_event as fe " + 
			" LEFT JOIN t_event_type as et on et.type_id = fe.fk_type_id " + 
			" LEFT JOIN t_event_area as ea on ea.area_id = fe.fk_area_id " + 
			" WHERE if(?1 != '',et.type_name = ?1 ,1+1) " + 
			" and if(?2 != '',fe.event_level = ?2 ,1+1) " + 
			" and if(?3 != '',ea.area_name = ?3 ,1+1) " + 
			" and if(?4 != 0,fe.event_state = ?4 ,1+1)",nativeQuery=true)
	public List<FirstEventEntity> findFirstEventsByEventTypeEventLevelEventAreaState(String typeName,String eventLevel,String areaName,int eventState);
}
