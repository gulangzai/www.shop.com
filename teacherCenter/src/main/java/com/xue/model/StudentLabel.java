/*
 * StudentLabel.java
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
public class StudentLabel {
    /**
    /*标签id
    */
    private Integer labelId;

    /**
    /*标签内容
    */
    private String labelContent;

    /**
    /*标签状态（1：公共标签。2个人标签。3老师标签）
    */
    private Integer labelStatus;

    /**
    /*操作人id
    */
    private String userId;

    private Date createTime;

    private Date updateTime;

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLabelContent() {
        return labelContent;
    }

    public void setLabelContent(String labelContent) {
        this.labelContent = labelContent == null ? null : labelContent.trim();
    }

    public Integer getLabelStatus() {
        return labelStatus;
    }

    public void setLabelStatus(Integer labelStatus) {
        this.labelStatus = labelStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}