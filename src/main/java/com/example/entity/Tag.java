package com.example.entity;


public class Tag {

	private String tag;
	private Integer number;
	public String gettag() {
		return tag;
	}
	public void settag(String tag) {
		this.tag = tag;
	}
	public Integer getnumber() {
		return number;
	}
	public void setnumber(Integer number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Tag [tag=" + tag + ", number=" + number + "]";
	}
	
}
