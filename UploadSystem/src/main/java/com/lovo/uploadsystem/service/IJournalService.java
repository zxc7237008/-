package com.lovo.uploadsystem.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.lovo.uploadsystem.entity.JournalEntity;





public interface IJournalService {



	  /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<JournalEntity> showjournalListPage(int pageNum,int pageSize);
    
    /**
     * 有参分页
     * @param too 参数
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<JournalEntity> showjournalListPageto(String too, int pageNum, int pageSize);
	
    
    /**
     * 返回总条数
     * @return
     */
	int getAllPage(int pageSize);
	
	int getAllPageto(String too,int pageSize);
/**
 * 添加table日志
 */
void savejournal(JournalEntity journalEntity);



}
