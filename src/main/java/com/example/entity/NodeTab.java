package com.example.entity;

import java.util.Date;


public class NodeTab {

	private Integer nodeTabId;
	private String nodeTabCode;
	private String nodeTabTitle;
	private String nodeTabDesc;
	private boolean isDelete;
	private Integer nodeTabOrder;
	private Date createDate;
	private Date updateDate;
	public Integer getNodeTabId() {
		return nodeTabId;
	}
	public void setNodeTabId(Integer nodeTabId) {
		this.nodeTabId = nodeTabId;
	}
	public String getNodeTabCode() {
		return nodeTabCode;
	}
	public void setNodeTabCode(String nodeTabCode) {
		this.nodeTabCode = nodeTabCode;
	}
	public String getNodeTabTitle() {
		return nodeTabTitle;
	}
	public void setNodeTabTitle(String nodeTabTitle) {
		this.nodeTabTitle = nodeTabTitle;
	}
	public String getNodeTabDesc() {
		return nodeTabDesc;
	}
	public void setNodeTabDesc(String nodeTabDesc) {
		this.nodeTabDesc = nodeTabDesc;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getNodeTabOrder() {
		return nodeTabOrder;
	}
	public void setNodeTabOrder(Integer nodeTabOrder) {
		this.nodeTabOrder = nodeTabOrder;
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
	@Override
	public String toString() {
		return "NodeTab [nodeTabId=" + nodeTabId + ", nodeTabCode=" + nodeTabCode + ", nodeTabTitle=" + nodeTabTitle
				+ ", nodeTabDesc=" + nodeTabDesc + ", isDelete=" + isDelete + ", nodeTabOrder=" + nodeTabOrder
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
}
