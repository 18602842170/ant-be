package com.ant.be.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.TypeDef;

import com.ant.be.support.JsonbType;

/**
 * 课时人员分配表
 * 
 * @author ouyangzidou
 *
 */
@Entity
@TypeDef(name = "JsonbType", typeClass = JsonbType.class)
public class CourseAllot implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_allot_seq")
	@SequenceGenerator(name = "course_allot_seq", sequenceName = "course_allot_seq", allocationSize = 1, initialValue = 1)
	public Long id;
	
	// 人员ID
	public Long userId;

	// 关联的课程ID
	public Long courseId;
	
	// 人员分类 1：教师 ，2：学员
	public String userType;

	public Boolean deleteFlg;
	
	public Boolean isCharge;

	@Temporal(TemporalType.TIMESTAMP)
	public Date creatDate;

	@Temporal(TemporalType.TIMESTAMP)
	public Date updateDate;

	public CourseAllot() {

	}

	public Boolean getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(Boolean isCharge) {
		this.isCharge = isCharge;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Boolean getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(Boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
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
