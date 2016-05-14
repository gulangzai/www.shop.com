/*
 * StudentPlanSubjectPointSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-10-22 Created
 */
package com.xue.model;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-10-22
 */
public class StudentPlanSubjectPointSet {
    private Integer id;

    private Integer pointId;

    private Integer planPointId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Integer getPlanPointId() {
        return planPointId;
    }

    public void setPlanPointId(Integer planPointId) {
        this.planPointId = planPointId;
    }
}