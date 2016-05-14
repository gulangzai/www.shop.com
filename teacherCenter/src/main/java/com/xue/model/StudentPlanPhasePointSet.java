/*
 * StudentPlanPhasePointSet.java
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
public class StudentPlanPhasePointSet {
    private Integer id;

    private Integer subjectPhaseId;

    private Integer planPointId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectPhaseId() {
        return subjectPhaseId;
    }

    public void setSubjectPhaseId(Integer subjectPhaseId) {
        this.subjectPhaseId = subjectPhaseId;
    }

    public Integer getPlanPointId() {
        return planPointId;
    }

    public void setPlanPointId(Integer planPointId) {
        this.planPointId = planPointId;
    }
}