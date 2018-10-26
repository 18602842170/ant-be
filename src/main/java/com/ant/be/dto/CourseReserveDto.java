package com.ant.be.dto;

import com.ant.be.entity.CourseReserve;

public class CourseReserveDto extends CourseReserve{
	
	private static final long serialVersionUID = 1L;
	
	private String courseName;
	
	// 当前页
	public int pageNum;
	// 每页的数量
	public int pageSize;
	
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	
}
