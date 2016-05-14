/*
 * BsNotice.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2016-01-17 Created
 */
package com.xue.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 公告信息
 * 
 * @author Mr.Ruan
 * @version 1.0 2016-01-17
 */
public class BsNotice {
    private Integer id;

    /**
    /*通告内容
    */
    private String annunciate;

    /**
    /*通知时间
    */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    /**
    /*发布人id
    */
    private Integer publishUserId;

    /**
    /*状态（0未发布。1发布。2.已结束3已删除。4其他）
    */
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnnunciate() {
        return annunciate;
    }

    public void setAnnunciate(String annunciate) {
        this.annunciate = annunciate == null ? null : annunciate.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(Integer publishUserId) {
        this.publishUserId = publishUserId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}