package com.lovo.uploadsystem.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lovo.uploadsystem.entity.JournalEntity;




public interface IJournalDao extends CrudRepository<JournalEntity, Integer>{
	  /**
	   * 用户分页
	   * @param pageable
	   * @return
	   */
	@Query("select j from JournalEntity j" )
	List<JournalEntity> showjournalListPage(Pageable pageable);

	
	
	
}
