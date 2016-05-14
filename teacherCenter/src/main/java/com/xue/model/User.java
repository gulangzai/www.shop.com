/*
 * User.java
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
public class User {
    /**
    /*用户id
    */
    private String userId;

    /**
    /*用户姓名
    */
    private String userName;

    /**
    /*用户密码
    */
    private String password;

    /**
    /*用户级别
    */
    private String userLevel;

    /**
    /*地址A
    */
    private String addresA;

    /**
    /*电话号码
    */
    private String telephoneNumber;

    /**
    /*手机号码
    */
    private String mobileNumber;

    /**
    /*传真号码
    */
    private String faxNumber;

    /**
    /*分机号
    */
    private String extensionNumber;

    /**
    /*个人中心（学习中心url）
    */
    private String url;

    /**
    /*用户logo图片
    */
    private String userImg;

    /**
    /*创建时间
    */
    private Date createDate;

    /**
    /*创建者id
    */
    private String createUser;

    /**
    /*最后更新时间
    */
    private Date lastUpdateDate;

    /**
    /*最后更新者
    */
    private String lastUpdateUser;
    
    /*部门*/
    private String organization;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel == null ? null : userLevel.trim();
    }

    public String getAddresA() {
        return addresA;
    }

    public void setAddresA(String addresA) {
        this.addresA = addresA == null ? null : addresA.trim();
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber == null ? null : telephoneNumber.trim();
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber == null ? null : mobileNumber.trim();
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber == null ? null : faxNumber.trim();
    }

    public String getExtensionNumber() {
        return extensionNumber;
    }

    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber == null ? null : extensionNumber.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg == null ? null : userImg.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	} 
}