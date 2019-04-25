package com.lovo.uploadsystem.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lovo.uploadsystem.entity.FormValueEntity;


public interface FormValueDao extends CrudRepository<FormValueEntity, String>{

	@Query(value="SELECT * FROM t_form_value WHERE fk_event_no = ?1",nativeQuery=true)
	public FormValueEntity findValue(String eventId);

	@Modifying
	@Query(value="DELETE FROM `t_form_value` WHERE (`fk_event_no`=?1)",nativeQuery=true)
	public void delValue(String firstEventNo);
}
