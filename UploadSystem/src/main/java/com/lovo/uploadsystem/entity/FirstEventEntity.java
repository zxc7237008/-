package com.lovo.uploadsystem.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="t_first_event")
@Entity
public class FirstEventEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3635995913148885667L;

	@Id
	@Column(length=32)
	private String firstEventNo;//事件初报编号
	
	@Column(length=24)
	private String eventName;//事件名
	
	@Column(columnDefinition="char")
	private String eventLevel;//事件等级
	
	@Column(length=16)
	private String discoverer;//报警人
	
	@Column(length=13)
	private String discovererTel;//报警人电话
	
	@Column(length=255)
	private String detailAddr;//详细地址
	
	@Column(length=16)
	private String reporter;//上报人
	
	private int casualty;//伤亡人数
	
	private int eventState;//事件状态
	
	@Column(columnDefinition="datetime")
	private String eventDatetime;//事件记录时间
	
	@ManyToOne
	@JoinColumn(name="fk_area_id")
	private EventAreaEntity eventArea;//事件区域
	
	@ManyToOne
	@JoinColumn(name="fk_type_id")
	private EventTypeEntity eventType;//事件类型
	
	@OneToMany(mappedBy="firstEvent")
	private Set<FormValueEntity> valueSet;//表单值

	public String getFirstEventNo() {
		return firstEventNo;
	}

	public void setFirstEventNo(String firstEventNo) {
		this.firstEventNo = firstEventNo;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(String eventLevel) {
		this.eventLevel = eventLevel;
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

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public int getCasualty() {
		return casualty;
	}

	public void setCasualty(int casualty) {
		this.casualty = casualty;
	}

	public int getEventState() {
		return eventState;
	}

	public void setEventState(int eventState) {
		this.eventState = eventState;
	}

	public String getEventDatetime() {
		return eventDatetime;
	}

	public void setEventDatetime(String eventDatetime) {
		this.eventDatetime = eventDatetime;
	}

	public EventAreaEntity getEventArea() {
		return eventArea;
	}

	public void setEventArea(EventAreaEntity eventArea) {
		this.eventArea = eventArea;
	}

	public EventTypeEntity getEventType() {
		return eventType;
	}

	public void setEventType(EventTypeEntity eventType) {
		this.eventType = eventType;
	}

	public Set<FormValueEntity> getValueSet() {
		return valueSet;
	}

	public void setValueSet(Set<FormValueEntity> valueSet) {
		this.valueSet = valueSet;
	}
	
	
	
	
}
