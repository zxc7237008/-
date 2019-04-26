package com.lovo.uploadsystem.service;

import com.lovo.uploadsystem.entity.FormValueEntity;

public interface IFormValueService {
	/**
	 * 保存表单值
	 * @param formValue
	 */
	public void saveValue(FormValueEntity formValue);
	
	/**
	 * 查找指定事件的表单值
	 * @param eventId 事件编号
	 * @return 表单值
	 */
	public FormValueEntity findValue(String eventId);

	/**
	 * 通过事件编号删除指定的表单值
	 * @param firstEventNo 事件编号
	 */
	public void delValue(String firstEventNo);
	
}
