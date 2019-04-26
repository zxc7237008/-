package com.lovo.uploadsystem.dao;

import com.lovo.uploadsystem.entity.EventAreaEntity;
import com.lovo.uploadsystem.entity.JournalEntity;
import com.lovo.uploadsystem.entity.RosterEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRosterDao extends CrudRepository<RosterEntity, String> {

    /**
     * 值班分页
     * @param pageable
     * @return
     */
    @Query("select r from RosterEntity  r order by r.startDate desc " )
    List<RosterEntity> showRosterListPage(Pageable pageable);
}
