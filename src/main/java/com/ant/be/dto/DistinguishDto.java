package com.ant.be.dto;

import java.util.Map;

import com.ant.be.entity.Distinguish;

public class DistinguishDto extends Distinguish {
	// 当前页
	private int pageNum;
	// 每页的数量
	private int pageSize;
	private String name;
	private String num;
	
	// 创建姓名
	private String createName;
	// 更新姓名
	private String updateName;
	
	//创建时间
	private String createDateStr;
	//更新时间
	private String updateDateStr;
	
	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getUpdateDateStr() {
		return updateDateStr;
	}

	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

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
