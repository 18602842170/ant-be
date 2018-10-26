package com.ant.be.dto;

import java.util.Map;

import com.ant.be.entity.Role;

public class RoleDto extends Role {
	// 当前页
	private int pageNum;
	// 每页的数量
	private int pageSize;
	private String name;
	private String num;
	
	private Map<String, String> permissionMap;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Map<String, String> getPermissionMap() {
		return permissionMap;
	}

	public void setPermissionMap(Map<String, String> permissionMap) {
		this.permissionMap = permissionMap;
	}


}
