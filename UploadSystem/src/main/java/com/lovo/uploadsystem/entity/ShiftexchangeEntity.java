package com.lovo.uploadsystem.entity;
/**
 * 测试！！！！！
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_shiftexchange")
public class ShiftexchangeEntity {
	//登录模块
	@Id
	@Column(name="s_id",length=32)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//值班人名字
	@Column(name="s_name",length=32)
	private String name;
	//值班人密码
	@Column(name="s_password",length=32)
	private String password;
	//值班人日志
	@Column(name="s_daily",length=32)
	private String daily;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDaily() {
		return daily;
	}
	public void setDaily(String daily) {
		this.daily = daily;
	}
	
	
}
