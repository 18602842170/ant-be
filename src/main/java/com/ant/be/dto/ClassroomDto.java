package com.ant.be.dto;
import com.ant.be.entity.Classroom;
/**
 * 教室DTO
 * @author xujianxia
 *
 */
public class ClassroomDto extends Classroom {
	private static final long serialVersionUID = 1L;
	// 当前页
	private int pageNum;
	// 每页的数量
	private int pageSize;
	
	private String schoolName;

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

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	
}
