package com.example.enums;

public enum InsertReplyEnum {

	SUCCESS(1, "comment successful"), NO_USER(0,"ban"),REPEAT_USER(-1, "post failure"),INNER_ERROR(-2, "system error");
	
	private int state;
	private String stateInfo;

	private InsertReplyEnum(int state,String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	
	public static InsertReplyEnum stateOf(int index) {
		for(InsertReplyEnum state : values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
