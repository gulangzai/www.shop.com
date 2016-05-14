/*
 * BsUserPayInfo.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2016-01-17 Created
 */
package com.xue.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户充值信息
 * 
 * @author Mr.Ruan
 * @version 1.0 2016-01-17
 */
public class BsUserPayInfo {
    private Integer id;

    /**
    /*充值金额
    */
    private Integer payMoney;

    /**
    /*充值时间
    */
    @DateTimeFormat(pattern="yyyy-MM-ss HH:mm:ss")
    private Date payTime;

    /**
    /*充值原因
    */
    private String payCause;

    /**
    /*提供充值用户id
    */
    private String payUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Integer payMoney) {
        this.payMoney = payMoney;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayCause() {
        return payCause;
    }

    public void setPayCause(String payCause) {
        this.payCause = payCause == null ? null : payCause.trim();
    }

    public String getPayUserId() {
        return payUserId;
    }

    public void setPayUserId(String payUserId) {
        this.payUserId = payUserId == null ? null : payUserId.trim();
    }
}