package com.lovo.uploadsystem.entity;

import java.util.Set;




public class PowerEntity{
    private String pid;
	private String pname;
	private String puri;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPuri() {
		return puri;
	}
	public void setPuri(String puri) {
		this.puri = puri;
	}
	public String getSysTag() {
		return sysTag;
	}
	public void setSysTag(String sysTag) {
		this.sysTag = sysTag;
	}
	public Set<RolePowerEntity> getSetRolePower() {
		return setRolePower;
	}
	public void setSetRolePower(Set<RolePowerEntity> setRolePower) {
		this.setRolePower = setRolePower;
	}
	private String sysTag;
	private Set<RolePowerEntity> setRolePower;
	
}