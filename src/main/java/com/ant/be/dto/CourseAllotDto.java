package com.ant.be.dto;

import com.ant.be.entity.CourseAllot;

public class CourseAllotDto extends CourseAllot{
	
	private static final long serialVersionUID = 1L;
	
	private String courseName;
	private String userName;
	// 当前页
	private int pageNum;

	// 每页的数量
	private int pageSize;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
