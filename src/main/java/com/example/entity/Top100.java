package com.example.entity;


public class Top100 {

	private String userName;
	private Integer score;
	private String avatar;

	private Integer countTopic;
	

	private Integer countReply;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getCountTopic() {
		return countTopic;
	}

	public void setCountTopic(Integer countTopic) {
		this.countTopic = countTopic;
	}

	public Integer getCountReply() {
		return countReply;
	}

	public void setCountReply(Integer countReply) {
		this.countReply = countReply;
	}

	@Override
	public String toString() {
		return "Top100 [userName=" + userName + ", score=" + score + ", avatar=" + avatar + ", countTopic=" + countTopic
				+ ", countReply=" + countReply + "]";
	}
	
}
