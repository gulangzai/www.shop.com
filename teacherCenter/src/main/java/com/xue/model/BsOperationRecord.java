/*
 * BsOperationRecord.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

import java.util.Date;

/**
 * 操作记录表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class BsOperationRecord {
    private Integer id;

    /**
    /*操作记录名称
    */
    private String operationRecordName;

    /**
    /*操作时间
    */
    private Date operationTime;

    /**
    /*状态
    */
    private Integer statu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperationRecordName() {
        return operationRecordName;
    }

    public void setOperationRecordName(String operationRecordName) {
        this.operationRecordName = operationRecordName == null ? null : operationRecordName.trim();
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }
}