package com.lovo.uploadsystem.service;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.lovo.uploadsystem.entity.EventAreaEntity;


public interface IEventAreaService {

public List<EventAreaEntity> getArea();
	
	public EventAreaEntity findArea(String areaId);
	
	/**
	 * 查询所有地区信息
	 * @return 地区信息集合
	 */
	public List<EventAreaEntity> findAllEventAreas();

	void saveEventArea(List<EventAreaEntity> listeventAreaEntity);
	
	

    /**

     * @param in

     * @param fileName

     * 处理上传的excel文件

     *

     * */
    public  List<List<Object>> getresourceListByExcel(InputStream in, String fileName) throws Exception;

    /**
     * 判断excel文件的格式
     * @param inStr
     * @param fileName
     * @return
     */
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception;
	
    

	  /**
   * 分页
   * @param pageNum
   * @param pageSize
   * @return
   */
  List<EventAreaEntity> showEventAreaListPage(int pageNum,int pageSize);
  
  /**
   * 有参分页
   * @param too 参数
   * @param pageNum
   * @param pageSize
   * @return
   */
  List<EventAreaEntity> showEventAreaListPageto(String too, int pageNum, int pageSize);
	
  
  /**
   * 返回总条数
   * @return
   */
	int getAllPage(int pageSize);
	
	int getAllPageto(String too,int pageSize);
	
	/**
	 * 根据用户ID删除用户
	 * @param id
	 */
	public  void  delectEvenArea(String id);
}
