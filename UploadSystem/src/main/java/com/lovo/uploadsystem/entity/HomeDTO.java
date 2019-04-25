package com.lovo.uploadsystem.entity;

import java.util.List;

public class HomeDTO {
	private List<EventTypeEntity> eventlist;
	private List<EventAreaEntity> areaList;
	private List<StateEntity> stateList;
	private List<FirstEventAreaEventTypeDTO> dtoList;
	private PageBean<FirstEventAreaEventTypeDTO> pageBean;
	
	public HomeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HomeDTO(List<EventTypeEntity> eventlist, List<EventAreaEntity> areaList, List<StateEntity> stateList,
			List<FirstEventAreaEventTypeDTO> dtoList, PageBean<FirstEventAreaEventTypeDTO> pageBean) {
		super();
		this.eventlist = eventlist;
		this.areaList = areaList;
		this.stateList = stateList;
		this.dtoList = dtoList;
		this.pageBean = pageBean;
	}

	public List<EventTypeEntity> getEventlist() {
		return eventlist;
	}
	public void setEventlist(List<EventTypeEntity> eventlist) {
		this.eventlist = eventlist;
	}
	public List<EventAreaEntity> getAreaList() {
		return areaList;
	}
	public void setAreaList(List<EventAreaEntity> areaList) {
		this.areaList = areaList;
	}
	public List<StateEntity> getStateList() {
		return stateList;
	}
	public void setStateList(List<StateEntity> stateList) {
		this.stateList = stateList;
	}
	public List<FirstEventAreaEventTypeDTO> getDtoList() {
		return dtoList;
	}
	public void setDtoList(List<FirstEventAreaEventTypeDTO> dtoList) {
		this.dtoList = dtoList;
	}
	public PageBean<FirstEventAreaEventTypeDTO> getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean<FirstEventAreaEventTypeDTO> pageBean) {
		this.pageBean = pageBean;
	}
	
}
