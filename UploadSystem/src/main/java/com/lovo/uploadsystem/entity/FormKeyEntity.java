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

@Table(name="t_form_key")
@Entity
public class FormKeyEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7648907392615641879L;

	@Id
	@Column(length=32)
	@GenericGenerator(name="keyUUid", strategy="uuid")
	@GeneratedValue(generator="keyUUid")
	private String keyId;//表单名主键id
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="fk_type_id")
	private EventTypeEntity eventType;//事件类型
	
	@Column(length=16)
	private String key1;//字段1
	
	@Column(length=16)
	private String key2;//字段2
	
	@Column(length=16)
	private String key3;//字段3
	
	@Column(length=16)
	private String key4;//字段4
	
	@Column(length=16)
	private String key5;//字段5
	
	@Column(length=16)
	private String key6;//字段6

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public EventTypeEntity getEventType() {
		return eventType;
	}

	public void setEventType(EventTypeEntity eventType) {
		this.eventType = eventType;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public String getKey3() {
		return key3;
	}

	public void setKey3(String key3) {
		this.key3 = key3;
	}

	public String getKey4() {
		return key4;
	}

	public void setKey4(String key4) {
		this.key4 = key4;
	}

	public String getKey5() {
		return key5;
	}

	public void setKey5(String key5) {
		this.key5 = key5;
	}

	public String getKey6() {
		return key6;
	}

	public void setKey6(String key6) {
		this.key6 = key6;
	}
	
	
}
