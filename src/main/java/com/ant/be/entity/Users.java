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
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
	@SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1, initialValue = 1)
	public Long id;

	/**
	 * 用户名
	 */
	public String name;

	/**
	 * 昵称
	 */
	public String nikeName;

	/**
	 * 密码
	 */
	public String password;
	
	/**
	 * 加密密码的盐
	 */
	public String salt;

	/**
	 * 角色id
	 */
	public Long roleId;

	/**
	 * 部门id
	 */
	public Long deptId;
	
	/**
	 * sessionId
	 */
	public String sessionId;
	
	/**
	 * 电话号码
	 */
	public String phoneNumber;
	
	/**
	 * 验证电话
	 */
	public Boolean phoneCheck;
	
	/**
	 * 用户分类
	 */
	// 人员分类 1：教师 ，2：学员
	public String userType;
	
	/**
	 * 微信用
	 */
	public String wechatSessionId;
	
	public String wechatAvatarUrl;
	
	public String wechatCity;
	
	public String wechatprovince;
	
	public String wechatCountry;
	
	public Long wechatGender;
	
	public String wechatNickName;
	
	public String wechatOpenId;
	
	public String wechatUnionid;

	public Boolean deleteFlg;
	
	public String verifyCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date verifyDate;

	@Temporal(TemporalType.TIMESTAMP)
	public Date creatDate;

	@Temporal(TemporalType.TIMESTAMP)
	public Date updateDate;

	public Users() {

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

	public Boolean getPhoneCheck() {
		return phoneCheck;
	}

	public void setPhoneCheck(Boolean phoneCheck) {
		this.phoneCheck = phoneCheck;
	}

	public String getWechatSessionId() {
		return wechatSessionId;
	}

	public void setWechatSessionId(String wechatSessionId) {
		this.wechatSessionId = wechatSessionId;
	}

	public String getWechatOpenId() {
		return wechatOpenId;
	}

	public void setWechatOpenId(String wechatOpenId) {
		this.wechatOpenId = wechatOpenId;
	}

	public String getWechatUnionid() {
		return wechatUnionid;
	}

	public void setWechatUnionid(String wechatUnionid) {
		this.wechatUnionid = wechatUnionid;
	}

	public String getWechatAvatarUrl() {
		return wechatAvatarUrl;
	}

	public void setWechatAvatarUrl(String wechatAvatarUrl) {
		this.wechatAvatarUrl = wechatAvatarUrl;
	}

	public String getWechatCity() {
		return wechatCity;
	}

	public void setWechatCity(String wechatCity) {
		this.wechatCity = wechatCity;
	}

	public String getWechatprovince() {
		return wechatprovince;
	}

	public void setWechatprovince(String wechatprovince) {
		this.wechatprovince = wechatprovince;
	}

	public String getWechatCountry() {
		return wechatCountry;
	}

	public void setWechatCountry(String wechatCountry) {
		this.wechatCountry = wechatCountry;
	}

	public String getWechatNickName() {
		return wechatNickName;
	}

	public void setWechatNickName(String wechatNickName) {
		this.wechatNickName = wechatNickName;
	}

	public Long getWechatGender() {
		return wechatGender;
	}

	public void setWechatGender(Long wechatGender) {
		this.wechatGender = wechatGender;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}
	

}
