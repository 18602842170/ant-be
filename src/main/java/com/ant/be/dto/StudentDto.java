package com.ant.be.dto;
import com.ant.be.entity.Student;
/**
 * 教师DTO
 * @author xujianxia
 *
 */
public class StudentDto extends Student {
	private static final long serialVersionUID = 1L;
	// 当前页
	private int pageNum;
	// 每页的数量
	private int pageSize;
	
	private String courseId;
	
	/**
	 * 学员名称
	 */
	public String studentName;
	/**
	 * 学员电话
	 */
	public String studentPhone;

	/**
	 * 学员性别名称
	 */
	public String studentSexName;
	
	private String courseName;
	
	private String studentGradeName;
	
	public String getStudentGradeName() {
		return studentGradeName;
	}

	public void setStudentGradeName(String studentGradeName) {
		this.studentGradeName = studentGradeName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getStudentSexName() {
		return studentSexName;
	}

	public void setStudentSexName(String studentSexName) {
		this.studentSexName = studentSexName;
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
