/*
 * StudentDetailed.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

import java.util.Date;

/**
 * 学员详细信息表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class StudentDetailed {
    /**
    /*详细学员id
    */
    private String studentDetailedId;

    /**
    /*所属学员id
    */
    private String studentId;

    /**
    /*学员真实姓名
    */
    private String studentRealName;

    /**
    /*生日
    */
    private Date birthday;

    /**
    /*地址
    */
    private String address;

    /**
    /*班级
    */
    private String classInfo;

    /**
    /*身份证
    */
    private String idcard;

    /**
    /*行业类别
    */
    private String industryType;

    /**
    /*职位名称
    */
    private String jobName;

    /**
    /*职位时间
    */
    private Date jobTime;

    /**
    /*工作描述
    */
    private String jobDesc;

    /**
    /*薪资水平
    */
    private String salavg;

    /**
    /*座机
    */
    private String telePhone;

    /**
    /*微博
    */
    private String weibo;

    /**
    /*微信
    */
    private String weixin;

    /**
    /*企鹅qq
    */
    private String qq;

    /**
    /*邮件发送时间
    */
    private Date emailDate;

    /**
    /*邮件验证码
    */
    private String sysCode;

    /**
    /*是否激活邮箱
    */
    private String activeEmail;

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
    private String level;

    /**
    /*账户余额
    */
    private Float accountBalance;

    /**
    /*是否删除（0，未删除；1，已删除）
    */
    private String isDel;

    public String getStudentDetailedId() {
        return studentDetailedId;
    }

    public void setStudentDetailedId(String studentDetailedId) {
        this.studentDetailedId = studentDetailedId == null ? null : studentDetailedId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getStudentRealName() {
        return studentRealName;
    }

    public void setStudentRealName(String studentRealName) {
        this.studentRealName = studentRealName == null ? null : studentRealName.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo == null ? null : classInfo.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
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

    public Date getJobTime() {
        return jobTime;
    }

    public void setJobTime(Date jobTime) {
        this.jobTime = jobTime;
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

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone == null ? null : telePhone.trim();
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

    public Date getEmailDate() {
        return emailDate;
    }

    public void setEmailDate(Date emailDate) {
        this.emailDate = emailDate;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }

    public String getActiveEmail() {
        return activeEmail;
    }

    public void setActiveEmail(String activeEmail) {
        this.activeEmail = activeEmail == null ? null : activeEmail.trim();
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }
}