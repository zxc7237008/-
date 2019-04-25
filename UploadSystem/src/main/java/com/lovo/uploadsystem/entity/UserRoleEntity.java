package com.lovo.uploadsystem.entity;






public class UserRoleEntity {

	private String urId;

  
   
    private UserEntity user;
    
  
    private RoleEntity role;


	public String getUrId() {
		return urId;
	}


	public void setUrId(String urId) {
		this.urId = urId;
	}


	public UserEntity getUser() {
		return user;
	}


	public void setUser(UserEntity user) {
		this.user = user;
	}


	public RoleEntity getRole() {
		return role;
	}


	public void setRole(RoleEntity role) {
		this.role = role;
	}

	
	
	
}