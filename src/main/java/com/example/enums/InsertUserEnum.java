package com.example.enums;

/**
 *
 * Data dictionary for registered users
 * @Chao Wang
 * 09/10/2020
 * 19:52:11
 * TODO
 */
public enum InsertUserEnum {

	SUCCESS(1, "register success"), NO_USER(0,"register \n" +
			"failure"),REPEAT_USER(-1, "repeated register"),INNER_ERROR(-2, "system error");
	
	private int state;// state code
	private String stateInfo;// state info
	
	private InsertUserEnum(int state,String stateInfo) {
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
	
	public static InsertUserEnum stateOf(int index) {
		for(InsertUserEnum state : values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
