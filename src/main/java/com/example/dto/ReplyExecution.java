package com.example.dto;

import com.example.entity.Reply;
import com.example.enums.InsertReplyEnum;

/**
 * add result of comment
 * @author Jiangshan
 * TBD
 */
public class ReplyExecution {

	private String replyAuthorName;
	private int state;
	private String stateInfo;
	private Reply reply;
	
	public ReplyExecution() {
		super();
	}
	
	/**
	 *
	 * Constructor on failure
	 */
	public ReplyExecution(String replyAuthorName, InsertReplyEnum stateEnum) {
		this.replyAuthorName = replyAuthorName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	/**
	 *
	 * Constructor on success
	 */
	public ReplyExecution(String replyAuthorName,InsertReplyEnum stateEnum,Reply reply) {
		this.replyAuthorName = replyAuthorName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.reply = reply;
	}

	public String getReplyAuthorName() {
		return replyAuthorName;
	}

	public void setReplyAuthorName(String replyAuthorName) {
		this.replyAuthorName = replyAuthorName;
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

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "RootReplyExecution [replyAuthorName=" + replyAuthorName + ", state=" + state + ", stateInfo="
				+ stateInfo + ", reply=" + reply + "]";
	}

	
}
