package com.ant.be.dto;
import java.util.Date;

import com.ant.be.entity.Grade;
/**
 * 班级DTO
 * @author xujianxia
 *
 */
public class GradeDto extends Grade {
	private static final long serialVersionUID = 1L;
	// 当前页
	private int pageNum;
	// 每页的数量
	private int pageSize;

	/**
	 * 分类名称
	 */
	public String distiguishType;
	
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

	// 检索条件
	private int searchYear;
		
	// 检索条件
	private int searchMonth;
		
	// 检索条件
	private int searchDay;
	
	//显示年份
	private String yearTime;
	
	//显示开始时间
	private String startTime;
	
	//显示结束时间
	private String endTime;
	
	

	public String getYearTime() {
		return yearTime;
	}

	public void setYearTime(String yearTime) {
		this.yearTime = yearTime;
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

	public String getDistiguishType() {
		return distiguishType;
	}

	public void setDistiguishType(String distiguishType) {
		this.distiguishType = distiguishType;
	}
	
}
