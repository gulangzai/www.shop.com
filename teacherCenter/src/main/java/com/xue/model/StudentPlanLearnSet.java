/*
 * StudentPlanLearnSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class StudentPlanLearnSet {
    private Integer id;

    /**
    /*我的计划id
    */
    private Integer planId;

    /**
    /*我的学习记录id
    */
    private Integer planLearnId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getPlanLearnId() {
        return planLearnId;
    }

    public void setPlanLearnId(Integer planLearnId) {
        this.planLearnId = planLearnId;
    }
}