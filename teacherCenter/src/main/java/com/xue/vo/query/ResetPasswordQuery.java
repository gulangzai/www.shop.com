package com.xue.vo.query;

import javacommon.util.MD5Utils;

public class ResetPasswordQuery {
	
	private String mobile = null;
	
	private String defaultPassword = null;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDefaultPassword() {
		String password = "123456";
		try {
			defaultPassword = MD5Utils.createMD5(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}
	
	
}
