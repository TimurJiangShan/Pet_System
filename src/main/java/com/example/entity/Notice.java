package com.example.entity;

import java.util.Date;


public class Notice {

	private Integer noticeId;
	

	private String noticeTitle;

	private Boolean isRead;

	private Integer noticeAuthorId;
	

	private String noticeAuthorName;

	private Integer targetAuthorId;
	

	private String targetAuthorName;
	

	private Date createDate;

	private Date updateDate;

	private String noticeAction;
	

	private Integer topicId;

	private Integer topicAuthorId;

	private Integer topicSectionId;
	

	private String noticeContent;

	private String statusCd;

	private String title;
	

	private String avatar;	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Integer getNoticeAuthorId() {
		return noticeAuthorId;
	}

	public void setNoticeAuthorId(Integer noticeAuthorId) {
		this.noticeAuthorId = noticeAuthorId;
	}

	public String getNoticeAuthorName() {
		return noticeAuthorName;
	}

	public void setNoticeAuthorName(String noticeAuthorName) {
		this.noticeAuthorName = noticeAuthorName;
	}

	public Integer getTargetAuthorId() {
		return targetAuthorId;
	}

	public void setTargetAuthorId(Integer targetAuthorId) {
		this.targetAuthorId = targetAuthorId;
	}

	public String getTargetAuthorName() {
		return targetAuthorName;
	}

	public void setTargetAuthorName(String targetAuthorName) {
		this.targetAuthorName = targetAuthorName;
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

	public String getNoticeAction() {
		return noticeAction;
	}

	public void setNoticeAction(String noticeAction) {
		this.noticeAction = noticeAction;
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

	public Integer getTopicSectionId() {
		return topicSectionId;
	}

	public void setTopicSectionId(Integer topicSectionId) {
		this.topicSectionId = topicSectionId;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	@Override
	public String toString() {
		return "RootNotice [noticeId=" + noticeId + ", noticeTitle=" + noticeTitle + ", isRead=" + isRead
				+ ", noticeAuthorId=" + noticeAuthorId + ", noticeAuthorName=" + noticeAuthorName + ", targetAuthorId="
				+ targetAuthorId + ", targetAuthorName=" + targetAuthorName + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", noticeAction=" + noticeAction + ", topicId=" + topicId
				+ ", topicAuthorId=" + topicAuthorId + ", topicSectionId=" + topicSectionId + ", noticeContent="
				+ noticeContent + ", statusCd=" + statusCd + ", title=" + title + ", avatar=" + avatar + "]";
	}

}
