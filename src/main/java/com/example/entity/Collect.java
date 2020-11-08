package com.example.entity;

import java.util.Date;

/**
 * Collection entity
 * @author Cheng
 * 2018-06-29
 * 5:15:14 p.m
 * TODO
 */
public class Collect {

	/**
	 * Unique identification
	 */
	private Integer id;
	/**
	 * User ID
	 */
	private Integer uid;
	/**
	 * Topic ID
	 */
	private Integer tid;
	/**
	 * Creation time
	 */
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
