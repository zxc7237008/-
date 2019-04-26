package com.lovo.uploadsystem.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lovo.uploadsystem.dto.YearDto;

public class ListUtil {
	
	private static List<YearDto> yearList;
	
	static {
		
		yearList = new ArrayList<>();
		
		yearList.add(new YearDto("-01-01", "-04-01"));
		
		yearList.add(new YearDto("-04-02", "-07-01"));
		
		yearList.add(new YearDto("-07-02", "-10-01"));
		
		yearList.add(new YearDto("-10-02", "-12-31"));
	}
	
	public static List<YearDto> getYearList() {
		
		return yearList;
	}
	
}
