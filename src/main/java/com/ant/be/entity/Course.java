package com.ant.be.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ant.be.support.JsonbType;

/**
 * 课程entity
 * @author Administrator
 *
 */
@Entity
@TypeDef(name = "JsonbType", typeClass = JsonbType.class)
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
	@SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1, initialValue = 1)
	public Long id;
	
	/**
	 * 课程名称
	 */
	public String name;
	
	/**
	 * 价格
	 */
	public BigDecimal price;
	
	/**
	 * 背景图片
	 */
	public String backImageUrl;
	
	/**
	 * 精品课程
	 */
	public Boolean fine;
	
	/**
	 * 教师id
	 */
	@Column(columnDefinition = "jsonb")
	@Type(type = "JsonbType")
	public String teachers;
	
	/**
	 * 教师id
	 */

	public Long userId;
	/**
	 * 教室id
	 */

	public Long classroomId;
	

	/**
	 * 年级
	 */
	public Long gradeId;
	
	/**
	 * 科目
	 */
	public Long subjectId;
	/**
	 * 课时数
	 */
	public Long courseNumber;
	
	/**
	 * 课程人数
	 */
	public Long classesNumber;
	
	/**
	 * 年份
	 */
	public String year;
	
	/**
	 *年级分类id
	 */
	public Long gradeClassId;
	
	
	public Long getGradeClassId() {
		return gradeClassId;
	}

	public void setGradeClassId(Long gradeClassId) {
		this.gradeClassId = gradeClassId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(Long classroomId) {
		this.classroomId = classroomId;
	}
	
	public Long getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(Long courseNumber) {
		this.courseNumber = courseNumber;
	}
	/**
	 *教师id
	 */

	/**
	 * 缴费人数
	 */
	public Long payNumber;
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 预约人数
	 */
	public Long appointmentNumber;
	
	
	public Boolean deleteFlg;

	@Temporal(TemporalType.TIMESTAMP)
	public Date creatDate;

	@Temporal(TemporalType.TIMESTAMP)
	public Date updateDate;

	public Long getAppointmentNumber() {
		return appointmentNumber;
	}

	public void setAppointmentNumber(Long appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getBackImageUrl() {
		return backImageUrl;
	}

	public void setBackImageUrl(String backImageUrl) {
		this.backImageUrl = backImageUrl;
	}

	public Boolean getFine() {
		return fine;
	}

	public void setFine(Boolean fine) {
		this.fine = fine;
	}

	public Boolean getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(Boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	
	public String getTeachers() {
		return teachers;
	}

	public Long getClassesNumber() {
		return classesNumber;
	}

	public void setClassesNumber(Long classesNumber) {
		this.classesNumber = classesNumber;
	}

	public Long getPayNumber() {
		return payNumber;
	}

	public void setPayNumber(Long payNumber) {
		this.payNumber = payNumber;
	}

	

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	

}
