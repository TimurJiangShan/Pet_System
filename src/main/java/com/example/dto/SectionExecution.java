package com.example.dto;

import com.example.entity.NodeTab;
import com.example.enums.InsertEnum;
import com.example.enums.UpdateEnum;

/**
 * Store the results of the operation section
 * @author Jiangshan
 * TBD
 */
public class SectionExecution {

	private String sectionName;
	private int state;
	private String stateInfo;
	private NodeTab rootSection;
	
	public SectionExecution() {
		super();
	}

	//Add the constructor when the plate fails
	public SectionExecution(String sectionName, InsertEnum stateEnum) {
		this.sectionName = sectionName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	//update the constructor when the plate fails
	public SectionExecution(String sectionName, UpdateEnum stateEnum) {
		this.sectionName = sectionName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//Add the constructor when the plate success
	public SectionExecution(String sectionName,InsertEnum stateEnum,NodeTab rootSection) {
		this.sectionName = sectionName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.rootSection = rootSection;
	}
	
	//Add the constructor when the plate successful
	public SectionExecution(String sectionName,UpdateEnum stateEnum,NodeTab rootSection) {
		this.sectionName = sectionName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.rootSection = rootSection;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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

	public NodeTab getRootSection() {
		return rootSection;
	}

	public void setRootSection(NodeTab rootSection) {
		this.rootSection = rootSection;
	}

	@Override
	public String toString() {
		return "RootSectionExecution [sectionName=" + sectionName + ", state=" + state + ", stateInfo=" + stateInfo
				+ ", rootSection=" + rootSection + "]";
	}
	
}
