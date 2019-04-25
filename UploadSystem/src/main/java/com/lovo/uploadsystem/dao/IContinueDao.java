package com.lovo.uploadsystem.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lovo.uploadsystem.entity.ContinueEntity;



public interface IContinueDao extends CrudRepository<ContinueEntity, String>{

	/**
	 * 	根据事件初报的编号来查询当前事件的所有事件续报记录
	 * @param id 事件初报的编号
	 * @return	返回当前事件的所有续报集合
	 */
	
	@Query("select c from ContinueEntity c left join c.event e where e.firstEventNo=?1")
	public List<ContinueEntity> findAllContinueEntity(String id ,Pageable pageable);
	
	@Query("select c from ContinueEntity c left join c.event e where e.firstEventNo=?1")
	public List<ContinueEntity> findAllPage(String id);
	
	
}
