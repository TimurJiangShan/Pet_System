package com.example.entity;

import java.util.Date;


/**
 * @author Jiangshan Zhao
 */
public class Follow {

	/**
	 * Primary Key
	 */
	private Integer id;

	private Integer uid;

	private Integer fid;

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
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Follow [id=" + id + ", uid=" + uid + ", fid=" + fid + ", createDate=" + createDate + "]";
	}
	
}
