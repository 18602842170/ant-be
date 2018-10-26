package com.ant.be.dto;
import com.ant.be.entity.DepositTeacher;
/**
 * 托管学员DTO
 * 
 * @author ouyangzidou
 *
 */
public class DepositTeacherDto extends DepositTeacher {
	
	private static final long serialVersionUID = 1L;
	
	// 当前页
	private int pageNum;
	// 每页的数量
	private int pageSize;
	// 从用户表取出的教师姓名
	private String teacherNameInUser;
	// 教室名称
	private String classRoomName;
	// 托管时间组合起来的字符串
	private String depositTimeStr;

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

	public String getClassRoomName() {
		return classRoomName;
	}

	public void setClassRoomName(String classRoomName) {
		this.classRoomName = classRoomName;
	}

	public String getDepositTimeStr() {
		return depositTimeStr;
	}

	public void setDepositTimeStr(String depositTimeStr) {
		this.depositTimeStr = depositTimeStr;
	}

	public String getTeacherNameInUser() {
		return teacherNameInUser;
	}

	public void setTeacherNameInUser(String teacherNameInUser) {
		this.teacherNameInUser = teacherNameInUser;
	}
	
	

}
