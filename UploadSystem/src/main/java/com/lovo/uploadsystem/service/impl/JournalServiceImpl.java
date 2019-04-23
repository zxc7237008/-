package com.lovo.uploadsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lovo.uploadsystem.dao.IJournalDao;
import com.lovo.uploadsystem.entity.JournalEntity;
import com.lovo.uploadsystem.service.IJournalService;



@Service(value="journalService")
public class JournalServiceImpl implements IJournalService{
	@Autowired
	private IJournalDao journalDao;

	@Override
	public List<JournalEntity> showjournalListPage(int pageNum, int pageSize) {
        PageRequest page = new PageRequest(pageNum,pageSize);
        List<JournalEntity> list = journalDao.showjournalListPage(page);
        return list;
		
	}

	@Override
	public int getAllPage(int pageSize) {
		List<JournalEntity> list = (List<JournalEntity>) journalDao.findAll();
        int alljournalSize = list.size();
        if (alljournalSize % pageSize == 0){
            alljournalSize = alljournalSize / pageSize;
        }else {
            alljournalSize = alljournalSize / pageSize + 1;
        }
        return alljournalSize;
	}

	@Override
	public void savejournal(JournalEntity journalEntity) {
		
		journalDao.save(journalEntity);
	}

	
	
	



}
