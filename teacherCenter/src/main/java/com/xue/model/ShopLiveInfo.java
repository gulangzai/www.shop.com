/*
 * ShopLiveInfo.java
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
public class ShopLiveInfo {
    private Integer id;

    /**
    /*直播开始时间
    */
    private Date beginLiveTime;

    /**
    /*直播结束时间
    */
    private Date endLiveTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBeginLiveTime() {
        return beginLiveTime;
    }

    public void setBeginLiveTime(Date beginLiveTime) {
        this.beginLiveTime = beginLiveTime;
    }

    public Date getEndLiveTime() {
        return endLiveTime;
    }

    public void setEndLiveTime(Date endLiveTime) {
        this.endLiveTime = endLiveTime;
    }
}