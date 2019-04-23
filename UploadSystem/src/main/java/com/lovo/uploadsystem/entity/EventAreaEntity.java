package com.lovo.uploadsystem.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="t_event_area")
@Entity
public class EventAreaEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4538754928157680557L;

	@Id
	@Column(length=32)
	@GenericGenerator(name="areaUUid", strategy="uuid")
	@GeneratedValue(generator="areaUUid")
	private String areaId;//地区主键id
	
	@Column(length=32)
	private String areaName;//地区名
	
	@JsonIgnore
	@OneToMany(mappedBy="eventArea")
	private Set<FirstEventEntity> firstEvtSet;//事件初报集合

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Set<FirstEventEntity> getFirstEvtSet() {
		return firstEvtSet;
	}

	public void setFirstEvtSet(Set<FirstEventEntity> firstEvtSet) {
		this.firstEvtSet = firstEvtSet;
	}
	
	

}
