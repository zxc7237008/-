package com.lovo.uploadsystem.service;

import java.util.List;

import com.lovo.uploadsystem.entity.ContinueEntity;
import com.lovo.uploadsystem.entity.FirstEventEntity;


public interface IContinueService {

	/**
	 * 把续报内容存入数据库
	 * @param continueEntity 从页面获取的续报内容
	 * @return 返回存入数据库的续报内容
	 */
	public ContinueEntity saveContinueEntity(ContinueEntity continueEntity);
	
	/**
	 *  根据事件初报的编号来查询当前事件的所有续报记录
	 * @param id 事件初报的编号
	 * @return 返回当前事件的续报记录集合
	 */
	
	public List<ContinueEntity> findALLContinueEntity(String id,int pageNum, int pageSize);
	
	
	
	/**
	 * 根据事件编号来查询事件初报信息
	 * @param id 事件初报编号
	 * @return	返回事件初报信息
	 */
	public FirstEventEntity findFirstEvent(String id);
	
	
	public int getAllPage(int pageSize);
}
