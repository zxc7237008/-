package com.lovo.uploadsystem.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="t_form_value")
@Entity
public class FormValueEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4987965132504989119L;

	@Id
	@Column(length=32)
	@GenericGenerator(name="valueUUid", strategy="uuid")
	@GeneratedValue(generator="valueUUid")
	private String valueId;//表单值主键id
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="fk_event_no")
	private FirstEventEntity firstEvent;//事件初报实体
	
	@Column(length=24)
	private String value1;//数据1
	
	@Column(length=24)
	private String value2;//数据2
	
	@Column(length=24)
	private String value3;//数据3
	
	@Column(length=24)
	private String value4;//数据4
	
	@Column(length=24)
	private String value5;//数据5
	
	@Column(length=24)
	private String value6;//数据6

	public String getValueId() {
		return valueId;
	}

	public void setValueId(String valueId) {
		this.valueId = valueId;
	}


	public FirstEventEntity getFirstEvent() {
		return firstEvent;
	}

	public void setFirstEvent(FirstEventEntity firstEvent) {
		this.firstEvent = firstEvent;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	public String getValue5() {
		return value5;
	}

	public void setValue5(String value5) {
		this.value5 = value5;
	}

	public String getValue6() {
		return value6;
	}

	public void setValue6(String value6) {
		this.value6 = value6;
	}
	
	
}
