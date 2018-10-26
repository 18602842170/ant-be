package com.ant.be.dto;

import com.ant.be.entity.DepositScores;

public class DepositScoresDto extends DepositScores{
	
	private static final long serialVersionUID = 1L;
	
	private String searchDay;
	
	// 当前页
	private int pageNum;
	// 每页的数量
	private int pageSize;
	
	public String getSearchDay() {
		return searchDay;
	}

	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
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

}
