/*
 * StudentDays.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-09-10 Created
 */
package com.xue.model;

/**
 * 每天学习计划表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-09-10
 */
public class StudentDays {
    /**
    /*每天学习计划id
    */
    private Integer daysId;

    /**
    /*每天计划排序
    */
    private Integer orderBy;

    /**
    /*学习状态（0.未完成.1.已完成.2.错点学习）
    */
    private Integer status;

    /**
    /*学习的时间
    */
    private String xuexiDate;

    public Integer getDaysId() {
        return daysId;
    }

    public void setDaysId(Integer daysId) {
        this.daysId = daysId;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getXuexiDate() {
        return xuexiDate;
    }

    public void setXuexiDate(String xuexiDate) {
        this.xuexiDate = xuexiDate == null ? null : xuexiDate.trim();
    }
}