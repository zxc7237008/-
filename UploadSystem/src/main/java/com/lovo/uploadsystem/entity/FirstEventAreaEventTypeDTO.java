package com.lovo.uploadsystem.entity;

public class FirstEventAreaEventTypeDTO {
	
	private String eventName;
	
	private String typeName;
	
	private String eventLevel;
	
	private String areaName;
	
	private String discoverer;
	
	private String discovererTel;
	
	private String eventState;

	public FirstEventAreaEventTypeDTO() {
		super();
	}

	public FirstEventAreaEventTypeDTO(String eventName, String typeName, String eventLevel, String areaName,
			String discoverer, String discovererTel, String eventState) {
		super();
		this.eventName = eventName;
		this.typeName = typeName;
		this.eventLevel = eventLevel;
		this.areaName = areaName;
		this.discoverer = discoverer;
		this.discovererTel = discovererTel;
		this.eventState = eventState;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(String eventLevel) {
		this.eventLevel = eventLevel;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getDiscoverer() {
		return discoverer;
	}

	public void setDiscoverer(String discoverer) {
		this.discoverer = discoverer;
	}

	public String getDiscovererTel() {
		return discovererTel;
	}

	public void setDiscovererTel(String discovererTel) {
		this.discovererTel = discovererTel;
	}

	public String getEventState() {
		return eventState;
	}

	public void setEventState(String eventState) {
		this.eventState = eventState;
	}
	
}
