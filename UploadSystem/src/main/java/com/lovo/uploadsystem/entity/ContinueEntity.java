package com.lovo.uploadsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

	/**
	    *     事件续报实体
	 */

@Entity
@Table(name="t_continue")
public class ContinueEntity {
	
	
	//主键ID
	@Id
	@GenericGenerator(name = "continue", strategy = "uuid")
	@GeneratedValue(generator = "continue")
	@Column(name="c_id",unique = true,nullable=false,length=32)
	private String rId;
	
	
	//事件等级
	@Column(name="c_level",columnDefinition="char")
	private String level;
	
	//受伤人数
	@Column(name ="c_number")
	private Integer number;
	
	//续报日期
	@Column(name ="c_date",columnDefinition="datetime")
	private String date;
	
	
	//续报人
	@Column(name = "c_reporter",nullable = false, length = 16)
	private String reporter;
	
	
	//报警人电话
	@Column(name = "c_phoneNumber",nullable = false, length = 13)
	private String phoneNumber;
	
	//报警人姓名
	@Column(name = "c_name",nullable = false, length = 16)
	private String name;
	
	//详情
	@Column(name = "c_detailed",columnDefinition="Text")
	private String detailed;
	
	//FirstEventEntity事件编号
	@ManyToOne
	@JoinColumn(name = "c_event")
	private FirstEventEntity event;

	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetailed() {
		return detailed;
	}

	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}

	public FirstEventEntity getEvent() {
		return event;
	}

	public void setEvent(FirstEventEntity event) {
		this.event = event;
	}

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}
	
	
	
	
}
