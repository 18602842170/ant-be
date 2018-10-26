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
 * 班级表
 * @author xujianxia
 *
 */
@Entity
public class Grade{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_seq")
	@SequenceGenerator(name = "grade_seq", sequenceName = "grade_seq", allocationSize = 1, initialValue = 1)
	public Long id;


	/**
	 * 年份
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date gradeYear;
	/**
	 * 分类
	 */
	public Long distiguishId;
	/**
	 * 开始时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date startDate;
	/**
	 * 结束时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date endDate;

	public Boolean deleteFlg;

	@Temporal(TemporalType.TIMESTAMP)
	public Date creatDate;

	@Temporal(TemporalType.TIMESTAMP)
	public Date updateDate;

	public Grade() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getGradeYear() {
		return gradeYear;
	}

	public void setGradeYear(Date gradeYear) {
		this.gradeYear = gradeYear;
	}

	public Long getDistiguishId() {
		return distiguishId;
	}

	public void setDistiguishId(Long distiguishId) {
		this.distiguishId = distiguishId;
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

}
