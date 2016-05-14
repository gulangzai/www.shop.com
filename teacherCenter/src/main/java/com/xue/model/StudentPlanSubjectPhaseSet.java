/*
 * StudentPlanSubjectPhaseSet.java
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
public class StudentPlanSubjectPhaseSet {
    private Integer id;

    private Integer planId;

    private Integer subjectPhaseId;

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

    public Integer getSubjectPhaseId() {
        return subjectPhaseId;
    }

    public void setSubjectPhaseId(Integer subjectPhaseId) {
        this.subjectPhaseId = subjectPhaseId;
    }
}