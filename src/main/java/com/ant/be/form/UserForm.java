package com.ant.be.form;

import java.util.List;
import java.util.Map;

import com.ant.be.dto.PermissionDto;

public class UserForm {
	private Long id;

	// 用户名
	public String name;
	
	// 用户id
	public String userId;
	
	// 密码
	public String password;
	
	// 加密密码的盐
	private String salt;
	
	private Long roleId;
	
	private boolean permissionOfPage;
	
//	private List<PermissionDto> permissionList;
	private Map<String, String> permissionList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Map<String, String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(Map<String, String> permissionList) {
		this.permissionList = permissionList;
	}

	public boolean isPermissionOfPage() {
		return permissionOfPage;
	}

	public void setPermissionOfPage(boolean permissionOfPage) {
		this.permissionOfPage = permissionOfPage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}


//	public List<PermissionDto> getPermissionList() {
//		return permissionList;
//	}
//
//	public void setPermissionList(List<PermissionDto> permissionList) {
//		this.permissionList = permissionList;
//	}



	

}
