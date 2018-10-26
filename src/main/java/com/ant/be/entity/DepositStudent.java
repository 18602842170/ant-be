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
public class DepositStudent {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deposit_student_seq")
	@SequenceGenerator(name = "deposit_student_seq", sequenceName = "deposit_student_seq", allocationSize = 1, initialValue = 1)
	public Long id;

	// 被托管学员对应的用户表id
	public Long studentUserId;

	// 托管时间起
	@Temporal(TemporalType.TIMESTAMP)
	public Date depositStartDate;
	
	// 托管时间止
	@Temporal(TemporalType.TIMESTAMP)
	public Date depositEndDate;
	
	// 托管教室id
	public Long classRoomId;
	
	// 是否打餐flg 0：是，1：否
	public String foodFlag;

	public Boolean deleteFlg;

	@Temporal(TemporalType.TIMESTAMP)
	public Date creatDate;

	@Temporal(TemporalType.TIMESTAMP)
	public Date updateDate;

	public DepositStudent() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentUserId() {
		return studentUserId;
	}

	public void setStudentUserId(Long studentUserId) {
		this.studentUserId = studentUserId;
	}

	public Long getClassRoomId() {
		return classRoomId;
	}

	public void setClassRoomId(Long classRoomId) {
		this.classRoomId = classRoomId;
	}

	public String getFoodFlag() {
		return foodFlag;
	}

	public void setFoodFlag(String foodFlag) {
		this.foodFlag = foodFlag;
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


}
