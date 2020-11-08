package com.example.dto;

import com.example.entity.User;
import com.example.enums.InsertUserEnum;
import com.example.enums.UpdateUserEnum;



//Store the results of operating users
public class UserExecution {

	private String userName;
	private String password;
	private String email;
	private int state;// result state
	private String stateInfo;// state info
	private User user;//success
	
	public UserExecution() {
		super();
	}
	
	/**
	 *
	 * Constructor when registration fails
	 * @param userName
	 * @param stateEnum
	 */
	public UserExecution(String userName, InsertUserEnum stateEnum) {
		this.userName = userName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	} 
	
	/**
	 *
	 * Constructor when update fails
	 * @param userName
	 * @param stateEnum
	 */
	public UserExecution(String userName, UpdateUserEnum stateEnum) {
		this.userName = userName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	} 
	
	/**
	 *
	 * Constructor when registration successed
	 * @param userName
	 * @param stateEnum
	 * @param user
	 */
	public UserExecution(String userName,InsertUserEnum stateEnum,User user) {
		this.userName = userName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.user = user;
	}
	
	/**
	 *
	 * Constructor when update successed
	 * @param userName
	 * @param stateEnum
	 * @param user
	 */
	public UserExecution(String userName,UpdateUserEnum stateEnum,User user) {
		this.userName = userName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "RootUserExecution [userName=" + userName + ", password=" + password + ", email=" + email + ", state="
				+ state + ", stateInfo=" + stateInfo + ", user=" + user + "]";
	}
	
}
