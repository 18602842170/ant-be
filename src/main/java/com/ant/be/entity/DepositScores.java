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

@Entity
@TypeDef(name = "JsonbType", typeClass = JsonbType.class)
public class DepositScores implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deposit_scores_seq")
	@SequenceGenerator(name = "deposit_scores_seq", sequenceName = "deposit_scores_seq", allocationSize = 1, initialValue = 1)
	public Long id;

	// 学生托管表id
	public Long depositStudentId;
	
	// 分数日期 此日期为年月日的时间。(2018-10-15)
	public String scoresDateString;
	
	// A、学校作业：
	public int scoresA;
	
	// B、朗诵背诵：
	public int scoresB;
	
	// C、数感训练：
	public int scoresC;

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

	public Long getDepositStudentId() {
		return depositStudentId;
	}

	public void setDepositStudentId(Long depositStudentId) {
		this.depositStudentId = depositStudentId;
	}

	public String getScoresDateString() {
		return scoresDateString;
	}

	public void setScoresDateString(String scoresDateString) {
		this.scoresDateString = scoresDateString;
	}

	public int getScoresA() {
		return scoresA;
	}

	public void setScoresA(int scoresA) {
		this.scoresA = scoresA;
	}

	public int getScoresB() {
		return scoresB;
	}

	public void setScoresB(int scoresB) {
		this.scoresB = scoresB;
	}

	public int getScoresC() {
		return scoresC;
	}

	public void setScoresC(int scoresC) {
		this.scoresC = scoresC;
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
