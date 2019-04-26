package com.lovo.uploadsystem.service.impl;

import com.lovo.uploadsystem.dao.IRosterDao;
import com.lovo.uploadsystem.entity.JournalEntity;
import com.lovo.uploadsystem.entity.RosterEntity;
import com.lovo.uploadsystem.service.IRosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RosterServiceImpl implements IRosterService {

    @Autowired
    IRosterDao rosterDao;

    @Override
    public void insert(RosterEntity rosterEntity) {
      rosterDao.save(rosterEntity);
    }

    @Override
    public List<RosterEntity> showRosterListPage(Integer pageNo, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNo,pageSize);
        List<RosterEntity> list = rosterDao.showRosterListPage(pageRequest);
        return list;
    }

    @Override
    public int getAllPage(int pageSize) {

        List<RosterEntity> list = (List<RosterEntity>) rosterDao.findAll();
        int alljournalSize = list.size();
        if (alljournalSize % pageSize == 0){
            alljournalSize = alljournalSize / pageSize;
        }else {
            alljournalSize = alljournalSize / pageSize + 1;
        }
        return alljournalSize;

    }
}
