package com.lovo.uploadsystem.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
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
	
	@Query(value="SELECT fe.*,et.*,ea.* " + 
			" from t_first_event as fe " + 
			" LEFT JOIN t_event_type as et on et.type_id = fe.fk_type_id " + 
			" LEFT JOIN t_event_area as ea on ea.area_id = fe.fk_area_id " +
			" WHERE if(?1 != '',et.type_name = ?1 ,1+1) " + 
			" and if(?2 != '',fe.event_level = ?2 ,1+1) " + 
			" and if(?3 != '',ea.area_name = ?3 ,1+1) " + 
			" and if(?4 != 0,fe.event_state = ?4 ,1+1)" +
			" ORDER BY fe.event_state asc, fe.event_datetime DESC LIMIT ?5,?6",nativeQuery=true)
	public List<FirstEventEntity> findAllFirstEventsByPage(String typeName,String eventLevel,String areaName,int eventState,int pageNum, int pageSize);
	/**
	 * 根据条件查询当前显示页数
	 * @return
	 */
	@Query(value="SELECT fe.*,et.*,ea.* " + 
			" from t_first_event as fe " + 
			" LEFT JOIN t_event_type as et on et.type_id = fe.fk_type_id " + 
			" LEFT JOIN t_event_area as ea on ea.area_id = fe.fk_area_id " +
			" WHERE if(?1 != '',et.type_name = ?1 ,1+1) " + 
			" and if(?2 != '',fe.event_level = ?2 ,1+1) " + 
			" and if(?3 != '',ea.area_name = ?3 ,1+1) " + 
			" and if(?4 != 0,fe.event_state = ?4 ,1+1)",nativeQuery=true)
	public List<FirstEventEntity> findAllEventByState(String typeName,String eventLevel,String areaName,int eventState);
	
	/**
	 * 通过事件发生时间和类型统计当前时间段事件总数
	 * @return 事件总数
	 */
	
	@Query(value="SELECT count(fe.event_name) from t_first_event as fe LEFT JOIN t_event_type as et on et.type_id = fe.fk_type_id " + 
			"where fe.event_datetime between ?1 and ?2 " + 
			"and et.type_code = ?3",nativeQuery=true)
	public int findALLEventNumByTypeAndTime(String beginTime, String endTime, String typeCode);
	
	/**
	 * 统计该地区事件总数
	 * @return 事件总数
	 */
	
	@Query(value="select count(fe.fk_area_id) from t_first_event as fe LEFT JOIN t_event_area as tea on tea.area_id = fe.fk_area_id " + 
			"where tea.area_id = ?1",nativeQuery=true)
	public int findAllventNumByArea(String areaName);
}
