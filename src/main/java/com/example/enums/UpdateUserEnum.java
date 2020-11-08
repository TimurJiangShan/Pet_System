package com.example.enums;


public enum UpdateUserEnum {

	SUCCESS(1, "edit successful"), NO_USER(0,"edit failure"),REPEAT_USER(-1, "repeat operation"),INNER_ERROR(-2, "system error");
	
	private int state;
	private String stateInfo;
	
	private UpdateUserEnum(int state,String stateInfo) {	
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
	
	public static UpdateUserEnum stateOf(int index) {
		for(UpdateUserEnum state : values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
