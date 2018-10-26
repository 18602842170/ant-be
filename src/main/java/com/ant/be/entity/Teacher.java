package com.ant.be.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.TypeDef;

import com.ant.be.support.JsonbType;

@Entity
@TypeDef(name = "JsonbType", typeClass = JsonbType.class)
public class Teacher {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq")
	@SequenceGenerator(name = "teacher_seq", sequenceName = "teacher_seq", allocationSize = 1, initialValue = 1)
	public Long id;

	// 教师姓名
	public String teacherName;

	// 教师编号
	public String teacherCd;

	// 教师性别
	public String teacherSex;
	
	// 教师级别
	public String teacherLevel;
	
	// 关联的用户表的用户id
	public Long userId;
	
	// 教师简介
	@Column(length = 500)
	public String teacherProfile;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	public Date endDate;

	public Boolean deleteFlg;

	@Temporal(TemporalType.TIMESTAMP)
	public Date creatDate;

	@Temporal(TemporalType.TIMESTAMP)
	public Date updateDate;

	public Teacher() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherCd() {
		return teacherCd;
	}

	public void setTeacherCd(String teacherCd) {
		this.teacherCd = teacherCd;
	}

	public String getTeacherSex() {
		return teacherSex;
	}

	public void setTeacherSex(String teacherSex) {
		this.teacherSex = teacherSex;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public String getTeacherProfile() {
		return teacherProfile;
	}

	public void setTeacherProfile(String teacherProfile) {
		this.teacherProfile = teacherProfile;
	}

	public String getTeacherLevel() {
		return teacherLevel;
	}

	public void setTeacherLevel(String teacherLevel) {
		this.teacherLevel = teacherLevel;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
