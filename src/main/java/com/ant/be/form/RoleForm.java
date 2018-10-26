package com.ant.be.form;

import java.util.List;
import java.util.Map;

import com.ant.be.entity.Role;

public class RoleForm {
	private Integer id;

	private String num;

	private String name;

	private Integer version;

	private Map<String, String> permissionList;

	private List<Role> roleList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Map<String, String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(Map<String, String> permissionList) {
		this.permissionList = permissionList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

}
