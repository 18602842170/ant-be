package com.ant.be.dto;

import java.util.Date;

import com.ant.be.entity.RollCall;

public class RollCallDto extends RollCall{
	
	private static final long serialVersionUID = 1L;
	
	// 当前页
	private int pageNum;
	
	// 每页的数量
	private int pageSize;
	
	// 用户名
	private String userName;
	
	//课程id
	private Long courseId;
	
	// 检索条件
	private int searchYear;
		
	// 检索条件
	private int searchMonth;
		
	// 检索条件
	private int searchDay;
	
	//上课日
	private Date courseDay;
	private String courseDayStr;
	
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Date getCourseDay() {
		return courseDay;
	}

	public void setCourseDay(Date courseDay) {
		this.courseDay = courseDay;
	}

	public String getCourseDayStr() {
		return courseDayStr;
	}

	public void setCourseDayStr(String courseDayStr) {
		this.courseDayStr = courseDayStr;
	}
	public int getSearchYear() {
		return searchYear;
	}

	public void setSearchYear(int searchYear) {
		this.searchYear = searchYear;
	}

	public int getSearchMonth() {
		return searchMonth;
	}

	public void setSearchMonth(int searchMonth) {
		this.searchMonth = searchMonth;
	}

	public int getSearchDay() {
		return searchDay;
	}

	public void setSearchDay(int searchDay) {
		this.searchDay = searchDay;
	}
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	//上课时间
	private String startTime;
	private String endTime;
	
	
	private String saveList;

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

	public String getSaveList() {
		return saveList;
	}

	public void setSaveList(String saveList) {
		this.saveList = saveList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
