package com.ant.be.dto;

import com.ant.be.entity.Notice;

public class NoticeDto extends Notice{
	
	private static final long serialVersionUID = 1L;
	
	// 发布人姓名
	private String publishName;
	// 检索条件
	private int searchYear;
	
	// 检索条件
	private int searchMonth;
	
	// 检索条件
	private int searchDay;

	//发布日期
	private String noticeDateStr;
	
	//发布对象名称
	private String noticeTargetName;
	
	//学校通知
	private String schoolNoticeTargets;
	
	//教师通知
	private String teacherNoticeTargets;
	
	// 学员id
	private Long userOfStuId;

	// 当前页
	private int pageNum;
	// 每页的数量
	private int pageSize;
	
		
	public String getPublishName() {
		return publishName;
	}
	public void setPublishName(String publishName) {
		this.publishName = publishName;
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
	
	public String getNoticeDateStr() {
		return noticeDateStr;
	}
	public void setNoticeDateStr(String noticeDateStr) {
		this.noticeDateStr = noticeDateStr;
	}
	
	public String getNoticeTargetName() {
		return noticeTargetName;
	}
	public void setNoticeTargetName(String noticeTargetName) {
		this.noticeTargetName = noticeTargetName;
	}
	
	public String getSchoolNoticeTargets() {
		return schoolNoticeTargets;
	}
	public void setSchoolNoticeTargets(String schoolNoticeTargets) {
		this.schoolNoticeTargets = schoolNoticeTargets;
	}
	
	public String getTeacherNoticeTargets() {
		return teacherNoticeTargets;
	}
	public void setTeacherNoticeTargets(String teacherNoticeTargets) {
		this.teacherNoticeTargets = teacherNoticeTargets;
	}
	
	public Long getUserOfStuId() {
		return userOfStuId;
	}
	public void setUserOfStuId(Long userOfStuId) {
		this.userOfStuId = userOfStuId;
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
