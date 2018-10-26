package com.ant.be.dto;
import com.ant.be.entity.DepositStudent;
/**
 * 托管学员DTO
 * 
 * @author ouyangzidou
 *
 */
public class DepositStudentDto extends DepositStudent {
	
	private static final long serialVersionUID = 1L;
	
	// 当前页
	private int pageNum;
	// 每页的数量
	private int pageSize;
	// 从用户表取出的学员姓名
	private String studentNameInUser;
	// 教室名称
	private String classRoomName;
	// 托管时间组合起来的字符串
	private String depositTimeStr;
	// 是否打餐字符串
	private String needFoodStr;
	// 通过老师id查询托管学生
	private Long teacherUserId;
	
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

	public String getStudentNameInUser() {
		return studentNameInUser;
	}

	public void setStudentNameInUser(String studentNameInUser) {
		this.studentNameInUser = studentNameInUser;
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

	public String getNeedFoodStr() {
		return needFoodStr;
	}

	public void setNeedFoodStr(String needFoodStr) {
		this.needFoodStr = needFoodStr;
	}

	public Long getTeacherUserId() {
		return teacherUserId;
	}

	public void setTeacherUserId(Long teacherUserId) {
		this.teacherUserId = teacherUserId;
	}
	
	

}
