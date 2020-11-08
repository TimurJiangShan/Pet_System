package com.example.entity;

import java.util.Date;

public class User {

	private Integer userId;
	

	private String userName;

	private String password;

	private String userSex;

	private String userAddr;

	private Integer score;

	private String avatar;
	

	private String email;
	

	private String url;

	private String signature;
	

	private String thirdId;

	private Boolean receiveMsg;

	private Date createDate;
	

	private Date updateDate;

	private Boolean isBlock;
	

	private String thirdAccessToken;

	private String statusCd;
	

	private String loginIp;
	

	private String lastLoginIp;
	

	private String userType;

	private String remark;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getThirdId() {
		return thirdId;
	}

	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}

	public Boolean getReceiveMsg() {
		return receiveMsg;
	}

	public void setReceiveMsg(Boolean receiveMsg) {
		this.receiveMsg = receiveMsg;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Boolean getIsBlock() {
		return isBlock;
	}

	public void setIsBlock(Boolean isBlock) {
		this.isBlock = isBlock;
	}

	public String getThirdAccessToken() {
		return thirdAccessToken;
	}

	public void setThirdAccessToken(String thirdAccessToken) {
		this.thirdAccessToken = thirdAccessToken;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "RootUser [userId=" + userId + ", userName=" + userName + ", password=" + password + ", userSex="
				+ userSex + ", userAddr=" + userAddr + ", score=" + score + ", avatar=" + avatar + ", email=" + email
				+ ", url=" + url + ", signature=" + signature + ", thirdId=" + thirdId + ", receiveMsg=" + receiveMsg
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", isBlock=" + isBlock
				+ ", thirdAccessToken=" + thirdAccessToken + ", statusCd=" + statusCd + ", loginIp=" + loginIp
				+ ", lastLoginIp=" + lastLoginIp + ", userType=" + userType + ", remark=" + remark + "]";
	}
	
}
