/*
 * BsUserInfo.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

import java.util.Date;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class BsUserInfo {
    
	private String userId;
    /**
    /*用户姓名（账号）
    */
    private String userName;

    /**
    /*密码
    */
    private String password;

    /**
    /*最近登录时间
    */
    private Date latelyLoginTime;

    /**
    /*状态（1：启用，2：未启用，3：其他）
    */
    private Integer state;
    
    
  
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getLatelyLoginTime() {
        return latelyLoginTime;
    }

    public void setLatelyLoginTime(Date latelyLoginTime) {
        this.latelyLoginTime = latelyLoginTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}