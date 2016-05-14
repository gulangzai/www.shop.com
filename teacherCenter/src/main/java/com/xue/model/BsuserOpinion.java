/*
 * BsuserOpinion.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2016-01-27 Created
 */
package com.xue.model;

import java.util.Date;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2016-01-27
 */
public class BsuserOpinion {
    /**
    /*意见问题id
    */
    private Integer id;

    /**
    /*意见问题
    */
    private String opinionContent;

    /**
    /*创建时间
    */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpinionContent() {
        return opinionContent;
    }

    public void setOpinionContent(String opinionContent) {
        this.opinionContent = opinionContent == null ? null : opinionContent.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}