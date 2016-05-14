/*
 * MyCollectDetailPointSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 我的收藏计划知识点关联表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class MyCollectDetailPointSet {
    /**
    /*id
    */
    private Integer id;

    /**
    /*我的收藏id
    */
    private Integer collectId;

    /**
    /*收藏的知识点id
    */
    private Integer detailPointId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getDetailPointId() {
        return detailPointId;
    }

    public void setDetailPointId(Integer detailPointId) {
        this.detailPointId = detailPointId;
    }
}