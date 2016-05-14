/*
 * PointFlySet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 知识点视频关联表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class PointFlySet {
    /**
    /*id
    */
    private Integer id;

    /**
    /*知识点id
    */
    private Integer pointId;

    /**
    /*视频id
    */
    private Integer flyId;

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

    public Integer getFlyId() {
        return flyId;
    }

    public void setFlyId(Integer flyId) {
        this.flyId = flyId;
    }
}