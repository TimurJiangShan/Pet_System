package com.example.entity;

import java.util.Date;


public class Topic {

	private Integer topicId;
	

	private String ptab;

	private String tab;

	private String title;

	private String tag;

	private String content;

	private String excerpt;

	private Date createDate;

	private Date updateDate;
	

	private Date lastReplyTime;

	private String lastReplyAuthor;

	private Integer viewCount;
	

	private String author;

	private Boolean top;

	private Boolean good;
	

	private Boolean showStatus;

	private Integer replyCount;
	

	private Boolean isDelete;
	

	private Boolean tagIsCount;

	private Integer postGoodCount;

	private Integer postBadCount;
	

	private String statusCd;

	private String nodeSlug;
	

	private String nodeTitle;

	private String remark;
	

	private String avatar;
	
	private String url;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
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

	public Date getLastReplyTime() {
		return lastReplyTime;
	}

	public void setLastReplyTime(Date lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}

	public String getLastReplyAuthor() {
		return lastReplyAuthor;
	}

	public void setLastReplyAuthor(String lastReplyAuthor) {
		this.lastReplyAuthor = lastReplyAuthor;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getTop() {
		return top;
	}

	public void setTop(Boolean top) {
		this.top = top;
	}

	public Boolean getGood() {
		return good;
	}

	public void setGood(Boolean good) {
		this.good = good;
	}

	public Boolean getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Boolean showStatus) {
		this.showStatus = showStatus;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Boolean getTagIsCount() {
		return tagIsCount;
	}

	public void setTagIsCount(Boolean tagIsCount) {
		this.tagIsCount = tagIsCount;
	}

	public Integer getPostGoodCount() {
		return postGoodCount;
	}

	public void setPostGoodCount(Integer postGoodCount) {
		this.postGoodCount = postGoodCount;
	}

	public Integer getPostBadCount() {
		return postBadCount;
	}

	public void setPostBadCount(Integer postBadCount) {
		this.postBadCount = postBadCount;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getNodeSlug() {
		return nodeSlug;
	}

	public void setNodeSlug(String nodeSlug) {
		this.nodeSlug = nodeSlug;
	}

	public String getNodeTitle() {
		return nodeTitle;
	}

	public void setNodeTitle(String nodeTitle) {
		this.nodeTitle = nodeTitle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPtab() {
		return ptab;
	}

	public void setPtab(String ptab) {
		this.ptab = ptab;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", ptab=" + ptab + ", tab=" + tab + ", title=" + title + ", tag=" + tag
				+ ", content=" + content + ", excerpt=" + excerpt + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", lastReplyTime=" + lastReplyTime + ", lastReplyAuthor=" + lastReplyAuthor
				+ ", viewCount=" + viewCount + ", author=" + author + ", top=" + top + ", good=" + good
				+ ", showStatus=" + showStatus + ", replyCount=" + replyCount + ", isDelete=" + isDelete
				+ ", tagIsCount=" + tagIsCount + ", postGoodCount=" + postGoodCount + ", postBadCount=" + postBadCount
				+ ", statusCd=" + statusCd + ", nodeSlug=" + nodeSlug + ", nodeTitle=" + nodeTitle + ", remark="
				+ remark + ", avatar=" + avatar + ", url=" + url + "]";
	}
	
}
