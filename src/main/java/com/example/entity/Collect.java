package com.example.entity;

import java.util.Date;

//collect
public class Collect {

	//key
	private Integer id;

	//userid
	private Integer uid;

	//topic id
	private Integer tid;

	private Date createDate;
	
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Collect [id=" + id + ", uid=" + uid + ", tid=" + tid + ", createDate=" + createDate + "]";
	}
	
}
