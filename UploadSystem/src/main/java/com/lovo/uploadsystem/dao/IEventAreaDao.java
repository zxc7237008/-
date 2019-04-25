package com.lovo.uploadsystem.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lovo.uploadsystem.entity.EventAreaEntity;


public interface IEventAreaDao extends CrudRepository<EventAreaEntity, String>{
	 /**
	   * 用户分页
	   * @param pageable
	   * @return
	   */
	@Query("select j from EventAreaEntity j" )
	List<EventAreaEntity> showEvenAreaListPage(Pageable pageable);

	@Query("select j from EventAreaEntity j where j.areaName=?1 ")
	List<EventAreaEntity> showEvenAreaListPageto(String too,Pageable pageable);
	
	@Query("select j from EventAreaEntity j where j.areaName=?1 " )
	List<EventAreaEntity> getAllPageto(String too);
	
	/**
	 * 根据用户ID删除用户
	 * @param id
	 */
//	@Modifying
//	@Query(value="delete from t_event_area  where area_id=?1",nativeQuery=true)
//	public void  delectEvenArea(String id);
	
	@Modifying
	@Query("delete from EventAreaEntity e where e.id=?1")
	public void  delectEvenAreato(String id);
	
}
