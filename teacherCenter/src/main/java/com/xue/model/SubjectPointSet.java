/*
 * SubjectPointSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 科目知识点关联表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class SubjectPointSet {
    /**
    /*所属科目id
    */
    private Integer detailId;

    /**
    /*所属知识点id
    */
    private String pointId;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId == null ? null : pointId.trim();
    }
}