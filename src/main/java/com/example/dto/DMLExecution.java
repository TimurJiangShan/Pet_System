package com.example.dto;

import com.example.enums.InsertEnum;
import com.example.enums.UpdateEnum;

/**
 *Store the result of curd
 * @author Jiangshan
 * TBD
 */
public class DMLExecution {

	private String type;
	private int state;
	private String stateInfo;
	private Object data;
	
	private DMLExecution(){}
	
	/**
	 *
	 * Constructor when the add is failure
	 * @param type
	 * @param stateEnum
	 */
	public DMLExecution(String type, InsertEnum stateEnum) {
		this.type = type;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	/**
	 *
	 * Constructor when the update is failer
	 * @param type
	 * @param stateEnum
	 */
	public DMLExecution(String type, UpdateEnum stateEnum) {
		this.type = type;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	/**
	 *
	 * Constructor when the add is successful
	 * @param type
	 * @param stateEnum
	 * @param data
	 */
	public DMLExecution(String type,InsertEnum stateEnum,Object data) {
		this.type = type;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.data = data;
	}
	
	/**
	 *Constructor when the update is successful
	 * @param type
	 * @param stateEnum
	 * @param data
	 */
	public DMLExecution(String type,UpdateEnum stateEnum,Object data) {
		this.type = type;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.data = data;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "InsertExecution [type=" + type + ", state=" + state + ", stateInfo=" + stateInfo + ", data=" + data
				+ "]";
	}
	
}
