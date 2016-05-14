package com.xue.model;

/*通知表*/
public class Notice {
	
	public String noticeId; 
	/*
	 * 通知主题
	 * */
	public String noticeTopic;
	
	/*
	 * 发送通知的人
	 * */
	public String noticePeople;
	
	/*
	 *通知内容
	 * */
	public String noticeContent;
	
	/*通知日期*/
	public String noticeDate;

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTopic() {
		return noticeTopic;
	}

	public void setNoticeTopic(String noticeTopic) {
		this.noticeTopic = noticeTopic;
	}

	public String getNoticePeople() {
		return noticePeople;
	}

	public void setNoticePeople(String noticePeople) {
		this.noticePeople = noticePeople;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}
	
}
