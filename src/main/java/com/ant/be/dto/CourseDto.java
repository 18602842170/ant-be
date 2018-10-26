package com.ant.be.dto;

import com.ant.be.entity.Course;

public class CourseDto extends Course{
	
	private static final long serialVersionUID = 1L;
	
	// 当前页
	private int pageNum;
	// 每页的数量
	private int pageSize;
	// 课时数量
	public long courseDetailCount;
	// 课程预约数量
	public long courseReserveCount;
	// 已上课时
	public long courseCount;
	// 字段有值时查询课时数量
	public String searchCourseDetailCount;
	// 字段有值时查询课程预约数量
	public String searchCourseReserveCount;
	
	public Long searchPersonalCourseByUserId;
	
	
	private String subjectName;
	
	private String gradeName;
	private String userName;
	private String gradeClassName;
	
	

	private String  classroomName;
	
	public String getGradeClassName() {
		return gradeClassName;
	}
	public void setGradeClassName(String gradeClassName) {
		this.gradeClassName = gradeClassName;
	}
	public String getClassroomName() {
		return classroomName;
	}
	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
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
	public long getCourseDetailCount() {
		return courseDetailCount;
	}
	public void setCourseDetailCount(long courseDetailCount) {
		this.courseDetailCount = courseDetailCount;
	}
	public long getCourseReserveCount() {
		return courseReserveCount;
	}
	public void setCourseReserveCount(long courseReserveCount) {
		this.courseReserveCount = courseReserveCount;
	}
	public String getSearchCourseDetailCount() {
		return searchCourseDetailCount;
	}
	public void setSearchCourseDetailCount(String searchCourseDetailCount) {
		this.searchCourseDetailCount = searchCourseDetailCount;
	}
	public String getSearchCourseReserveCount() {
		return searchCourseReserveCount;
	}
	public void setSearchCourseReserveCount(String searchCourseReserveCount) {
		this.searchCourseReserveCount = searchCourseReserveCount;
	}
	public Long getSearchPersonalCourseByUserId() {
		return searchPersonalCourseByUserId;
	}
	public void setSearchPersonalCourseByUserId(Long searchPersonalCourseByUserId) {
		this.searchPersonalCourseByUserId = searchPersonalCourseByUserId;
	}
	
	

}
