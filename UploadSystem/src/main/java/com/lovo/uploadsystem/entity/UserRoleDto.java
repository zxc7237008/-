package com.lovo.uploadsystem.entity;

import java.util.List;

public class UserRoleDto {
	//根据角色ID查询出拥有的用户
	List<UserEntity> listUser;
	//根据角色ID查询出不拥有的用户
	List<UserEntity> nolistUser;
	String roleName;
	String roleId;
	public List<UserEntity> getListUser() {
		return listUser;
	}
	public void setListUser(List<UserEntity> listUser) {
		this.listUser = listUser;
	}
	public List<UserEntity> getNolistUser() {
		return nolistUser;
	}
	public void setNolistUser(List<UserEntity> nolistUser) {
		this.nolistUser = nolistUser;
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
