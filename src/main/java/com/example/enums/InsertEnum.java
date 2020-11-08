package com.example.enums;

public enum InsertEnum {

	SUCCESS(1, "Added successfully"), NO_USER(0,"Added failure"),REPEAT_USER(-1, "Add repeatedly"),INNER_ERROR(-2, "system error");
	
	private int state;
	private String stateInfo;
	
	private InsertEnum(int state,String stateInfo) {
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
	
	public static InsertEnum stateOf(int index) {
		for(InsertEnum state : values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
