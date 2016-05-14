/*
 * StudentPlanChange.java
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
public class StudentPlanChange {
    /**
    /*计划修改id
    */
    private Integer planChangeId;

    /**
    /*变化名称
    */
    private String changeName;

    /**
    /*变化时间
    */
    private Date changeDate;

    public Integer getPlanChangeId() {
        return planChangeId;
    }

    public void setPlanChangeId(Integer planChangeId) {
        this.planChangeId = planChangeId;
    }

    public String getChangeName() {
        return changeName;
    }

    public void setChangeName(String changeName) {
        this.changeName = changeName == null ? null : changeName.trim();
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }
}