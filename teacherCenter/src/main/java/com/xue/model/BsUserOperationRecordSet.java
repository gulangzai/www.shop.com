/*
 * BsUserOperationRecordSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 用户操作记录关联表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class BsUserOperationRecordSet {
    private Integer id;

    private Integer userId;

    /**
    /*操作记录id
    */
    private Integer operationRecirdId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOperationRecirdId() {
        return operationRecirdId;
    }

    public void setOperationRecirdId(Integer operationRecirdId) {
        this.operationRecirdId = operationRecirdId;
    }
}