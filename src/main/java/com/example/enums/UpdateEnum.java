package com.example.enums;

public enum UpdateEnum {

	SUCCESS(1, "update successful"), NO_USER(0,"update failure"),REPEAT_USER(-1, "repeat operation"),INNER_ERROR(-2, "system error");
	
	private int state;
	private String stateInfo;
	
	private UpdateEnum(int state,String stateInfo) {	
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
	
	public static UpdateEnum stateOf(int index) {
		for(UpdateEnum state : values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
