package com.lovo.uploadsystem.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lovo.uploadsystem.entity.ShiftexchangeEntity;


public interface IShiftexchangeDao extends CrudRepository<ShiftexchangeEntity, Integer>{
	  /**
	   * 用户分页
	   * @param pageable
	   * @return
	   */
	@Query("select s from ShiftexchangeEntity s" )
	List<ShiftexchangeEntity> showshiftexListPage(Pageable pageable);

	@Query("select s from ShiftexchangeEntity s where s.name=?1 or s.daily=?1 ")
	List<ShiftexchangeEntity> showshiftexListPageto(String too,Pageable pageable);
	
	@Query("select s from ShiftexchangeEntity s where s.name=?1 or s.daily=?1 " )
	List<ShiftexchangeEntity> getAllPageto(String too);
}
