package com.lovo.uploadsystem.service;

import java.util.List;

import com.lovo.uploadsystem.entity.ShiftexchangeEntity;

public interface IShiftexchangeService {
	void savejournal(ShiftexchangeEntity shiftexchangeEntity);
	
	  /**
   * 分页
   * @param pageNum
   * @param pageSize
   * @return
   */
  List<ShiftexchangeEntity> showshiftexListPage(int pageNum,int pageSize);
  
  /**
   * 有参分页
   * @param too 参数
   * @param pageNum
   * @param pageSize
   * @return
   */
  List<ShiftexchangeEntity> showshiftexListPageto(String too, int pageNum, int pageSize);
  
  /**
   * 返回总条数
   * @return
   */
	int getAllPage(int pageSize);
	/**
	 * 返回有参条数
	 * @param too
	 * @param pageSize
	 * @return
	 */
	int getAllPageto(String too,int pageSize);
}
