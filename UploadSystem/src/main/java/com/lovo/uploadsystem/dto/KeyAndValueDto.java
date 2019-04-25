package com.lovo.uploadsystem.dto;

import java.util.List;

import com.lovo.uploadsystem.entity.FormKeyEntity;
import com.lovo.uploadsystem.entity.FormValueEntity;

public class KeyAndValueDto {
	
	private List<FormKeyEntity> keyList;
	
	private FormValueEntity value;

	public List<FormKeyEntity> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<FormKeyEntity> keyList) {
		this.keyList = keyList;
	}

	public FormValueEntity getValue() {
		return value;
	}

	public void setValue(FormValueEntity value) {
		this.value = value;
	}

	public KeyAndValueDto(List<FormKeyEntity> keyList, FormValueEntity value) {
		this.keyList = keyList;
		this.value = value;
	}

	public KeyAndValueDto() {
	}
	
	

}
