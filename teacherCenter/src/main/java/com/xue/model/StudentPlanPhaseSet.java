/*
 * StudentPlanPhaseSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-10-16 Created
 */
package com.xue.model;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-10-16
 */
public class StudentPlanPhaseSet {
    private Integer id;

    /**
    /*计划ID
    */
    private Integer planId;

    /**
    /*计划阶段ID
    */
    private Integer phaseId;

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

    public Integer getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Integer phaseId) {
        this.phaseId = phaseId;
    }
}