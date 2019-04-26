package com.lovo.uploadsystem.service;

import com.lovo.uploadsystem.entity.RosterEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRosterService{

    void insert(RosterEntity rosterEntity);

    /***
     * 值班分页
     * @param pageNo   页码
     * @param pageSize 页容量
     * @return
     */
    List<RosterEntity> showRosterListPage(Integer pageNo, Integer pageSize);

    /**
     * 返回总条数
     * @return
     */
    int getAllPage(int pageSize);
}
