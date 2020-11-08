package com.example.entity;

import java.util.Date;


public class Reply {


	private Integer replyId;

	private Integer topicId;

	private Integer topicAuthorId;

	private String replyContent;

	private Date createDate;

	private Date updateDate;

	private Integer replyAuthorId;

	private String replyAuthorName;

	private Boolean isDelete;

	private Boolean isRead;

	private Boolean isShow;

	private Integer replyGoodCount;

	private Integer replyBadCount;

	private String replyType;

	private Integer replyReadCount;

	private String statusCd;

	private String avatar;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public Integer getTopicAuthorId() {
		return topicAuthorId;
	}

	public void setTopicAuthorId(Integer topicAuthorId) {
		this.topicAuthorId = topicAuthorId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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

	public Integer getReplyAuthorId() {
		return replyAuthorId;
	}

	public void setReplyAuthorId(Integer replyAuthorId) {
		this.replyAuthorId = replyAuthorId;
	}

	public String getReplyAuthorName() {
		return replyAuthorName;
	}

	public void setReplyAuthorName(String replyAuthorName) {
		this.replyAuthorName = replyAuthorName;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public Integer getReplyGoodCount() {
		return replyGoodCount;
	}

	public void setReplyGoodCount(Integer replyGoodCount) {
		this.replyGoodCount = replyGoodCount;
	}

	public Integer getReplyBadCount() {
		return replyBadCount;
	}

	public void setReplyBadCount(Integer replyBadCount) {
		this.replyBadCount = replyBadCount;
	}

	public String getReplyType() {
		return replyType;
	}

	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}

	public Integer getReplyReadCount() {
		return replyReadCount;
	}

	public void setReplyReadCount(Integer replyReadCount) {
		this.replyReadCount = replyReadCount;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", topicId=" + topicId + ", topicAuthorId=" + topicAuthorId
				+ ", replyContent=" + replyContent + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", replyAuthorId=" + replyAuthorId + ", replyAuthorName=" + replyAuthorName + ", isDelete=" + isDelete
				+ ", isRead=" + isRead + ", isShow=" + isShow + ", replyGoodCount=" + replyGoodCount
				+ ", replyBadCount=" + replyBadCount + ", replyType=" + replyType + ", replyReadCount=" + replyReadCount
				+ ", statusCd=" + statusCd + ", avatar=" + avatar + "]";
	}
	
}
