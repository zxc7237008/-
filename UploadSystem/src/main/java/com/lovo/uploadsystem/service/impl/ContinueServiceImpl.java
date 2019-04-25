package com.lovo.uploadsystem.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lovo.uploadsystem.dao.IContinueDao;
import com.lovo.uploadsystem.dao.IFirstEventDao;
import com.lovo.uploadsystem.entity.ContinueEntity;
import com.lovo.uploadsystem.entity.FirstEventEntity;
import com.lovo.uploadsystem.service.IContinueService;

@Service(value="continueService")
public class ContinueServiceImpl implements IContinueService {

	
	@Autowired
	public 	IContinueDao continueDao;
	@Autowired
	public IFirstEventDao firstEventDao;
	
	@Override
	public ContinueEntity saveContinueEntity(ContinueEntity continueEntity,String id) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		continueEntity.setDate(date);
		
		FirstEventEntity event = new FirstEventEntity();
		event.setFirstEventNo(id);
		continueEntity.setEvent(event);
		  return continueDao.save(continueEntity);  
	}

	@Override
	public FirstEventEntity findFirstEvent(String id) {
		return firstEventDao.findOne(id);
		
	}

	@Override
	public List<ContinueEntity> findALLContinueEntity(String id,int pageNum, int pageSize) {
		PageRequest page = new PageRequest(pageNum,pageSize);
		return continueDao.findAllContinueEntity(id,page);
	}

	@Override
	public int getAllPage(int pageSize,String id) {
		List<ContinueEntity> list = continueDao.findAllPage(id);
		int alljournalSize = list.size();
        if (alljournalSize % pageSize == 0){
            alljournalSize = alljournalSize / pageSize;
        }else {
            alljournalSize = alljournalSize / pageSize + 1;
        }
        return alljournalSize;
		
	}

}
