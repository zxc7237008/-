package com.lovo.uploadsystem.dto;

public class SeasonDto {
	
	private int season1;
	
	private int season2;
	
	private int season3;
	
	private int season4;

	public int getSeason1() {
		return season1;
	}

	public void setSeason1(int season1) {
		this.season1 = season1;
	}

	public int getSeason2() {
		return season2;
	}

	public void setSeason2(int season2) {
		this.season2 = season2;
	}

	public int getSeason3() {
		return season3;
	}

	public void setSeason3(int season3) {
		this.season3 = season3;
	}

	public int getSeason4() {
		return season4;
	}

	public void setSeason4(int season4) {
		this.season4 = season4;
	}

	public SeasonDto(int season1, int season2, int season3, int season4) {
		this.season1 = season1;
		this.season2 = season2;
		this.season3 = season3;
		this.season4 = season4;
	}

	public SeasonDto() {
		
	}
	
	

}
