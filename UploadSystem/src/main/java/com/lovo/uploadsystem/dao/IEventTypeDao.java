package com.lovo.uploadsystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lovo.uploadsystem.entity.EventTypeEntity;


public interface IEventTypeDao extends CrudRepository<EventTypeEntity, String>{

	@Query(value="SELECT * FROM `t_event_type` LIMIT ?1, ?2",nativeQuery=true)
	List<EventTypeEntity> getTypeByPage(int startIndex,int pageSize);

}
