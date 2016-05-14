/*
 * ShoopingCart.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

import java.util.Date;

/**
 * 购物车表，待定。
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class ShoopingCart {
    /**
    /*购物车所属人id，无登录购物时，存储会话ID
    */
    private String userId;

    /**
    /*创建时间
    */
    private Date createTime;

    /**
    /*是否删除（0,不删除。1，删除。）
    */
    private Integer isDelete;

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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}