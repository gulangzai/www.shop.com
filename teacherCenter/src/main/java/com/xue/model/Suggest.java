package com.xue.model;

/*意见表
 * */
public class Suggest {
	
	public String suggestId; 
	/*
	 * 通知主题
	 * */
	public String suggestTopic;
	
	/*
	 * 发送通知的人
	 * */
	public String suggestPeople;
	
	/*
	 *通知内容
	 * */
	public String suggestContent;
	
	/*通知日期*/
	public String suggestDate;

	public String getSuggestId() {
		return suggestId;
	}

	public void setSuggestId(String suggestId) {
		this.suggestId = suggestId;
	}

	public String getSuggestTopic() {
		return suggestTopic;
	}

	public void setSuggestTopic(String suggestTopic) {
		this.suggestTopic = suggestTopic;
	}

	public String getSuggestPeople() {
		return suggestPeople;
	}

	public void setSuggestPeople(String suggestPeople) {
		this.suggestPeople = suggestPeople;
	}

	public String getSuggestContent() {
		return suggestContent;
	}

	public void setSuggestContent(String suggestContent) {
		this.suggestContent = suggestContent;
	}

	public String getSuggestDate() {
		return suggestDate;
	}

	public void setSuggestDate(String suggestDate) {
		this.suggestDate = suggestDate;
	}
 
}
