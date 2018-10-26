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

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ant.be.support.JsonbType;

/**
 * 教室表
 * @author xujianxia
 *
 */
@Entity
public class Classroom{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classroom_seq")
	@SequenceGenerator(name = "classroom_seq", sequenceName = "classroom_seq", allocationSize = 1, initialValue = 1)
	public Long id;

	/**
	 * 教室人数
	 */
	public Long classroomPeople;

	/**
	 * 教室名称
	 */
	public String classroomName;
	/**
	 * 教室地址
	 */
	public String classroomAddress;

	public Boolean deleteFlg;

	@Temporal(TemporalType.TIMESTAMP)
	public Date creatDate;

	@Temporal(TemporalType.TIMESTAMP)
	public Date updateDate;

	public Classroom() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClassroomPeople() {
		return classroomPeople;
	}

	public void setClassroomPeople(Long classroomPeople) {
		this.classroomPeople = classroomPeople;
	}

	public String getClassroomName() {
		return classroomName;
	}

	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}

	public String getClassroomAddress() {
		return classroomAddress;
	}

	public void setClassroomAddress(String classroomAddress) {
		this.classroomAddress = classroomAddress;
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
