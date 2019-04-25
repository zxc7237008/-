package com.lovo.uploadsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


public class RolePowerEntity {

	

	private String rpid;

   private RoleEntity role;

   private PowerEntity power;

public String getRpid() {
	return rpid;
}

public void setRpid(String rpid) {
	this.rpid = rpid;
}

public RoleEntity getRole() {
	return role;
}

public void setRole(RoleEntity role) {
	this.role = role;
}

public PowerEntity getPower() {
	return power;
}

public void setPower(PowerEntity power) {
	this.power = power;
}


}