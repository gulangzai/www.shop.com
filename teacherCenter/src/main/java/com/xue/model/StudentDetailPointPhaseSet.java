/*
 * StudentDetailPointPhaseSet.java
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
public class StudentDetailPointPhaseSet {
    private Integer id;

    private Integer detailPointId;

    private Integer planPhaseId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDetailPointId() {
        return detailPointId;
    }

    public void setDetailPointId(Integer detailPointId) {
        this.detailPointId = detailPointId;
    }

    public Integer getPlanPhaseId() {
        return planPhaseId;
    }

    public void setPlanPhaseId(Integer planPhaseId) {
        this.planPhaseId = planPhaseId;
    }
}