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
 * 课程预约entity
 * @author Administrator
 *
 */
@Entity
@TypeDef(name = "JsonbType", typeClass = JsonbType.class)
public class CourseReserve implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_reserve_seq")
	@SequenceGenerator(name = "course_reserve_seq", sequenceName = "course_reserve_seq", allocationSize = 1, initialValue = 1)
	public Long id;
	
	/**
	 * 课程关联Id
	 */
	public Long courseId;
	
	/**
	 * 预约号
	 */
	public String reserveCode;
	
	/**
	 * 手机号
	 */
	public String phoneNumber;

	public Boolean deleteFlg;

	@Temporal(TemporalType.TIMESTAMP)
	public Date creatDate;

	@Temporal(TemporalType.TIMESTAMP)
	public Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getReserveCode() {
		return reserveCode;
	}

	public void setReserveCode(String reserveCode) {
		this.reserveCode = reserveCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
