package com.example.entity;

import java.util.Date;


public class UpDown {


	private Integer id;
	

	private Integer uid;

	private Integer tid;
	

	private boolean upDown;

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

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public boolean isUpDown() {
		return upDown;
	}

	public void setUpDown(boolean upDown) {
		this.upDown = upDown;
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
		return "UpDown [id=" + id + ", uid=" + uid + ", tid=" + tid + ", upDown=" + upDown + ", createDate="
				+ createDate + ", isDelete=" + isDelete + "]";
	}
	
}
