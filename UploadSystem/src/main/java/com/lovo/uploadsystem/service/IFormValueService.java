package com.lovo.uploadsystem.service;

import com.lovo.uploadsystem.entity.FormValueEntity;

public interface IFormValueService {
	/**
	 * 保存表单值
	 * @param formValue
	 */
	public void saveValue(FormValueEntity formValue);
	
}
