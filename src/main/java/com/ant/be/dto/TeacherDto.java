package com.ant.be.dto;
import com.ant.be.entity.Teacher;
/**
 * 教师DTO
 * @author ouyangzidou
 *
 */
public class TeacherDto extends Teacher {
	private static final long serialVersionUID = 1L;
	// 当前页
	private int pageNum;
	// 每页的数量
	private int pageSize;
	// 性别
	private String teacherSexStr;
	// 等级
	private String teacherLevelStr;
	// 前页面传参教师id
	private String teacherId;
	// 从用户表取出的教师姓名
	private String teacherNameInUser;

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

	public String getTeacherSexStr() {
		return teacherSexStr;
	}

	public void setTeacherSexStr(String teacherSexStr) {
		this.teacherSexStr = teacherSexStr;
	}

	public String getTeacherLevelStr() {
		return teacherLevelStr;
	}

	public void setTeacherLevelStr(String teacherLevelStr) {
		this.teacherLevelStr = teacherLevelStr;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherNameInUser() {
		return teacherNameInUser;
	}

	public void setTeacherNameInUser(String teacherNameInUser) {
		this.teacherNameInUser = teacherNameInUser;
	}
	
	

}
