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
public class Notice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notice_seq")
	@SequenceGenerator(name = "notice_seq", sequenceName = "notice_seq", allocationSize = 1, initialValue = 1)
	public Long id;

	// 关联的用户ID
	public Long userId;
	
	// 通知标题
	public String noticeTitle;
	
	//通知内容
	public String noticeContent;
	
	//通知对象 全校：-2，学员-1，老师：0
	public Long noticeTarget;

	public Long getNoticeTarget() {
		return noticeTarget;
	}

	public void setNoticeTarget(Long noticeTarget) {
		this.noticeTarget = noticeTarget;
	}

	public Boolean deleteFlg;

	@Temporal(TemporalType.TIMESTAMP)
	public Date creatDate;

	@Temporal(TemporalType.TIMESTAMP)
	public Date updateDate;

	public Notice() {

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

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
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
