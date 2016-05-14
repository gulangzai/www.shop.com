/*
 * StudentInfo.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-09-10 Created
 */
package com.xue.model;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-09-10
 */
public class StudentInfo {
    private String studentId;

    /**
    /*昵称（可当用户登录账号）暂定！有中文！
    */
    private String studentName;

    /**
    /*手机号（可当用户登录账号）
    */
    private String mobile;

    /**
    /*邮箱（可当用户登录账号）
    */
    private String email;

    /**
    /*状态（0.未登录；1已登录；2其他）
    */
    private String state;

    /**
    /*密码（md5）
    */
    private String password;

    /**
    /*上次密码
    */
    private String lastPwd;

    /**
    /*注册时间
    */
    private String createTime;

    /**
    /*上次登录时间
    */
    private String lastLoginTime;

    /**
    /*本次登录时间
    */
    private String currentLoginTime;

    /**
    /*cookie信息
    */
    private String cookieValue;

    /**
    /*是否删除（0，未删除；1，已删除）
    */
    private String isDel;
    
    /**
    /*md5密码
    */
    private String mdPassword;
    
   /*
    *  学员账户余额
    *  */
    private int balance = 0;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getLastPwd() {
        return lastPwd;
    }

    public void setLastPwd(String lastPwd) {
        this.lastPwd = lastPwd == null ? null : lastPwd.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime == null ? null : lastLoginTime.trim();
    }

    public String getCurrentLoginTime() {
        return currentLoginTime;
    }

    public void setCurrentLoginTime(String currentLoginTime) {
        this.currentLoginTime = currentLoginTime == null ? null : currentLoginTime.trim();
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue == null ? null : cookieValue.trim();
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

	public String getMdPassword() {
		return mdPassword;
	}

	public void setMdPassword(String mdPassword) {
		this.mdPassword = mdPassword == null ? null : mdPassword.trim();
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}