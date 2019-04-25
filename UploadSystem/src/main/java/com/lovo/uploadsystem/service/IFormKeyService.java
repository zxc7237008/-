package com.lovo.uploadsystem.service;

import com.lovo.uploadsystem.entity.FormKeyEntity;

public interface IFormKeyService {

	/**
	 * 保存表单
	 * @param key 表单实体
	 */
	public void saveKey(FormKeyEntity key);
}
