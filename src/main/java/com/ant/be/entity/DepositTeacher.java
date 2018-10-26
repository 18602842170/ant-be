package com.ant.be.entity;

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

@Entity
@TypeDef(name = "JsonbType", typeClass = JsonbType.class)
public class DepositTeacher {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deposit_teacher_seq")
	@SequenceGenerator(name = "deposit_teacher_seq", sequenceName = "deposit_teacher_seq", allocationSize = 1, initialValue = 1)
	public Long id;

	// 托管教师对应的用户表id
	public Long teacherUserId;

	// 托管时间起
	@Temporal(TemporalType.TIMESTAMP)
	public Date depositStartDate;
	
	// 托管时间止
	@Temporal(TemporalType.TIMESTAMP)
	public Date depositEndDate;
	
	// 托管教室id
	public Long classRoomId;

	public Boolean deleteFlg;

	@Temporal(TemporalType.TIMESTAMP)
	public Date creatDate;

	@Temporal(TemporalType.TIMESTAMP)
	public Date updateDate;

	public DepositTeacher() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClassRoomId() {
		return classRoomId;
	}

	public void setClassRoomId(Long classRoomId) {
		this.classRoomId = classRoomId;
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

	public Date getDepositStartDate() {
		return depositStartDate;
	}

	public void setDepositStartDate(Date depositStartDate) {
		this.depositStartDate = depositStartDate;
	}

	public Date getDepositEndDate() {
		return depositEndDate;
	}

	public void setDepositEndDate(Date depositEndDate) {
		this.depositEndDate = depositEndDate;
	}

	public Long getTeacherUserId() {
		return teacherUserId;
	}

	public void setTeacherUserId(Long teacherUserId) {
		this.teacherUserId = teacherUserId;
	}


}
