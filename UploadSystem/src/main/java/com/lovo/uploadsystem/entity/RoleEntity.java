package com.lovo.uploadsystem.entity;

import java.io.Serializable;
import java.util.Set;



public class RoleEntity implements Serializable{

	
   
	
	private static final long serialVersionUID = -1368923952237896087L;

	private String roleId;
   
	private String roleName;
   
	private String roleDescribe;
      
	
    private Set<UserRoleEntity> setUserRole;


	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public String getRoleDescribe() {
		return roleDescribe;
	}


	public void setRoleDescribe(String roleDescribe) {
		this.roleDescribe = roleDescribe;
	}


	public Set<UserRoleEntity> getSetUserRole() {
		return setUserRole;
	}


	public void setSetUserRole(Set<UserRoleEntity> setUserRole) {
		this.setUserRole = setUserRole;
	}



     
}