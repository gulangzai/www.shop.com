/*
 * PhasePointSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 阶段与知识点关联表

 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class PhasePointSet {
    private Long id;

    /**
    /*阶段id
    */
    private Integer phaseId;

    /**
    /*知识点id
    */
    private Integer pointId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Integer phaseId) {
        this.phaseId = phaseId;
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }
}