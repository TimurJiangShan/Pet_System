package com.example.dto;

import com.example.entity.Topic;
import com.example.enums.InsertTopicEnum;

public class TopicExecution {

	private String title;
	private int state;
	private String stateInfo;
	private Topic topic;
	
	public TopicExecution() {
		super();
	}
	
	//Add the constructor when the plate fails
	public TopicExecution(String title, InsertTopicEnum stateEnum) {
		this.title = title;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//constructor when the plate successful
	public TopicExecution(String title,InsertTopicEnum stateEnum,Topic topic) {
		this.title = title;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.topic = topic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "RootTopicExecution [title=" + title + ", state=" + state + ", stateInfo=" + stateInfo + ", topic="
				+ topic + "]";
	}
	
}
