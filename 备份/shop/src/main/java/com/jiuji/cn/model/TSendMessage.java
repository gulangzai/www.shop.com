package com.jiuji.cn.model;

import java.util.Date;

public class TSendMessage {
	
	private String FMobile = null;
	
	private String FCheckcode = null;
	
	private Date FTime = null;

	private String FIp = null;
	
    public TSendMessage(){}
    
	public TSendMessage(String mobile, String checkCode) {
		// TODO Auto-generated constructor stub
		this.FMobile = mobile;
		this.FCheckcode = checkCode;
	}

	public String getFMobile() {
		return FMobile;
	}

	public void setFMobile(String fMobile) {
		FMobile = fMobile;
	}

	public String getFCheckcode() {
		return FCheckcode;
	}

	public void setFCheckcode(String fCheckcode) {
		FCheckcode = fCheckcode;
	}

	public Date getFTime() {
		return FTime;
	}

	public void setFTime(Date fTime) {
		FTime = fTime;
	}

	public String getFIp() {
		return FIp;
	}

	public void setFIp(String fIp) {
		FIp = fIp;
	} 
}
