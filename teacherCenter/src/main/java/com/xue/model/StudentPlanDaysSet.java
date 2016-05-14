/*
 * StudentPlanDaysSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 模板与计划天数关联表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class StudentPlanDaysSet {
    /**
    /*模板id
    */
    private String planId;

    /**
    /*学习计划天数id
    */
    private Integer daysId;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public Integer getDaysId() {
        return daysId;
    }

    public void setDaysId(Integer daysId) {
        this.daysId = daysId;
    }
}