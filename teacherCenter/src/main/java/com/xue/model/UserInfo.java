/*
 * UserInfo.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

import java.util.Date;

/**
 * 用户信息表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class UserInfo {
    /**
    /*主键id
    */
    private String userId;

    /**
    /*用户名
    */
    private String userName;

    /**
    /*用户密码
    */
    private String passWord;

    /**
    /*真实姓名
    */
    private String realName;

    /**
    /*性别
    */
    private String sex;

    /**
    /*通讯地址
    */
    private String address;

    /**
    /*座机号
    */
    private String telePhone;

    /**
    /*手机号
    */
    private String mobile;

    /**
    /*出生年月日
    */
    private Date birthday;

    /**
    /*邮箱
    */
    private String email;

    /**
    /*班级
    */
    private String classInfo;

    /**
    /*身份证号
    */
    private String idCard;

    /**
    /*行业类别
    */
    private String industryType;

    /**
    /*职位名称
    */
    private String jobName;

    /**
    /*工作时间
    */
    private String jobTime;

    /**
    /*职位类别
    */
    private String jobType;

    /**
    /*工作描述
    */
    private String jobDesc;

    /**
    /*薪资水平
    */
    private String salavg;

    /**
    /*用户状态
    */
    private String userState;

    /**
    /*用户头像
    */
    private String userPic;

    /**
    /*微博
    */
    private String weibo;

    /**
    /*微信
    */
    private String weixin;

    /**
    /*QQ
    */
    private String qq;

    /**
    /*COOKIE信息
    */
    private String cookeValue;

    /**
    /*邮件发送时间
    */
    private Date emailData;

    /**
    /*邮件验证码
    */
    private String emailCode;

    /**
    /*是否激活邮箱(0:未激活 1：已激活)
    */
    private Integer activeEmail;

    /**
    /*充值卡余额
    */
    private Float balance;

    /**
    /*积分
    */
    private Integer credits;

    /**
    /*等级
    */
    private Integer userLevel;

    /**
    /*账号余额
    */
    private Float accountBalance;

    /**
    /*文化程度(001:本科 002:研究生 003:博士 004:大专高职 005:高中及以下)
    */
    private String culture;

    /**
    /*所学专业
    */
    private String subjectName;

    /**
    /*毕业时间
    */
    private Date graduationDate;

    /**
    /*单位地址
    */
    private String companyAddress;

    /**
    /*单位电话
    */
    private String companyTelePhone;

    /**
    /*单位传真
    */
    private String companyFaxNumber;

    /**
    /*单位邮编
    */
    private String companyZipCode;

    /**
    /*住宅邮编
    */
    private String zipCode;

    /**
    /*证件编号
    */
    private String certificateid;

    /**
    /*学员区分(0:网站学员 1:继续教育学员 2：直播网学员)
    */
    private Integer studenttype;

    /**
    /*上一次登陆时间
    */
    private Date lastLoginTime;

    /**
    /*当前登陆时间
    */
    private Date loginTime;

    /**
    /*创建账号时间
    */
    private Date createTime;

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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone == null ? null : telePhone.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo == null ? null : classInfo.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType == null ? null : industryType.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobTime() {
        return jobTime;
    }

    public void setJobTime(String jobTime) {
        this.jobTime = jobTime == null ? null : jobTime.trim();
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType == null ? null : jobType.trim();
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc == null ? null : jobDesc.trim();
    }

    public String getSalavg() {
        return salavg;
    }

    public void setSalavg(String salavg) {
        this.salavg = salavg == null ? null : salavg.trim();
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState == null ? null : userState.trim();
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic == null ? null : userPic.trim();
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo == null ? null : weibo.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getCookeValue() {
        return cookeValue;
    }

    public void setCookeValue(String cookeValue) {
        this.cookeValue = cookeValue == null ? null : cookeValue.trim();
    }

    public Date getEmailData() {
        return emailData;
    }

    public void setEmailData(Date emailData) {
        this.emailData = emailData;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode == null ? null : emailCode.trim();
    }

    public Integer getActiveEmail() {
        return activeEmail;
    }

    public void setActiveEmail(Integer activeEmail) {
        this.activeEmail = activeEmail;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture == null ? null : culture.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getCompanyTelePhone() {
        return companyTelePhone;
    }

    public void setCompanyTelePhone(String companyTelePhone) {
        this.companyTelePhone = companyTelePhone == null ? null : companyTelePhone.trim();
    }

    public String getCompanyFaxNumber() {
        return companyFaxNumber;
    }

    public void setCompanyFaxNumber(String companyFaxNumber) {
        this.companyFaxNumber = companyFaxNumber == null ? null : companyFaxNumber.trim();
    }

    public String getCompanyZipCode() {
        return companyZipCode;
    }

    public void setCompanyZipCode(String companyZipCode) {
        this.companyZipCode = companyZipCode == null ? null : companyZipCode.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getCertificateid() {
        return certificateid;
    }

    public void setCertificateid(String certificateid) {
        this.certificateid = certificateid == null ? null : certificateid.trim();
    }

    public Integer getStudenttype() {
        return studenttype;
    }

    public void setStudenttype(Integer studenttype) {
        this.studenttype = studenttype;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}