package com.lovo.uploadsystem.entity;
/**
 * 交班实体类
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.Spring;

@Entity
@Table(name="t_journal")
public class JournalEntity {
	//交班ID
	@Id
	@Column(name="j_id",length=32)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//当事人名字
	@Column(name="j_name",length=32)
	private String name;
	//交班事件名字
	@Column(name="j_incident",length=32)
	private String incident;
	//事件时间
	@Column(name="j_data",length=32)
	private String data;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIncident() {
		return incident;
	}
	public void setIncident(String incident) {
		this.incident = incident;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	

	
	
	


	
}
