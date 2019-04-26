package com.lovo.uploadsystem.entity;

import java.util.List;

public class RolePowerDto {
	//该角色拥有的权限
	List<PowerEntity> listRolePower;
	
	//该角色不拥有的权限
	List<PowerEntity> noListRolePower;
	
	String roleName;
	
	String roleId;

	public List<PowerEntity> getListRolePower() {
		return listRolePower;
	}

	public void setListRolePower(List<PowerEntity> listRolePower) {
		this.listRolePower = listRolePower;
	}

	public List<PowerEntity> getNoListRolePower() {
		return noListRolePower;
	}

	public void setNoListRolePower(List<PowerEntity> noListRolePower) {
		this.noListRolePower = noListRolePower;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	
	
	

}
