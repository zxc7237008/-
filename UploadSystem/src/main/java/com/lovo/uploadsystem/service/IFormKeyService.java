package com.lovo.uploadsystem.service;

import com.lovo.uploadsystem.entity.FormKeyEntity;

public interface IFormKeyService {

	/**
	 * 保存表单
	 * @param key 表单实体
	 */
	public void saveKey(FormKeyEntity key);

	/**
	 * 通过事件类型id删除表单
	 * @param typeId 事件类型id
	 */
	public void delKey(String typeId);
}
