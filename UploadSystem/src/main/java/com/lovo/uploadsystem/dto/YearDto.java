package com.lovo.uploadsystem.dto;

public class YearDto {
	
	private String beginTime;
	
	private String endTime;

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public YearDto(String beginTime, String endTime) {
		this.beginTime = beginTime;
		this.endTime = endTime;
	}

	public YearDto() {
		
	}
	
	

}
