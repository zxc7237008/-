package com.lovo.uploadsystem.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lovo.uploadsystem.entity.FormKeyEntity;

public interface IFormKeyDao extends CrudRepository<FormKeyEntity, String>{

	@Modifying
	@Query(value="DELETE FROM t_form_key WHERE fk_type_id = ?1",nativeQuery=true)
	public void delKey(String typeId);

}
