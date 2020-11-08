package com.example.entity;

import java.util.Date;


public class Visit {

	private Integer id;
	

	private Integer uid;

	private Integer vid;
	

	private Date createDate;

	private boolean isDelete;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "Visit [id=" + id + ", uid=" + uid + ", vid=" + vid + ", createDate=" + createDate + ", isDelete="
				+ isDelete + "]";
	}
	
}
