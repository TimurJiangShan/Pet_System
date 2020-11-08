package com.example.entity;

import java.util.Date;


public class ReplyAndTopicByName {

	private Date createDate;
	private String author;
	private Integer topicId;
	private String title;
	private String replyContent;
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	@Override
	public String toString() {
		return "ReplyAndTopicByName [createDate=" + createDate + ", author=" + author + ", topicId=" + topicId
				+ ", title=" + title + ", replyContent=" + replyContent + "]";
	}
	
}
