/*
 * ViewMoiveInfo.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 观看记录信息表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class ViewMoiveInfo {
    /**
    /*观看记录id
    */
    private Integer viewId;

    /**
    /*上次观看时间
    */
    private String firstViewTime;

    /**
    /*观看开始时间
    */
    private String viewBeginTime;

    /**
    /*观看结束时间
    */
    private String viewEndTime;

    /**
    /*观看时长
    */
    private String viewTime;

    /**
    /*知识点id
    */
    private Integer pointId;

    /**
    /*商品详细id
    */
    private Integer shopDetailId;

    /**
    /*学员id
    */
    private String studentId;

    /**
    /*所属视频id
    */
    private Integer flyId;

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }

    public String getFirstViewTime() {
        return firstViewTime;
    }

    public void setFirstViewTime(String firstViewTime) {
        this.firstViewTime = firstViewTime == null ? null : firstViewTime.trim();
    }

    public String getViewBeginTime() {
        return viewBeginTime;
    }

    public void setViewBeginTime(String viewBeginTime) {
        this.viewBeginTime = viewBeginTime == null ? null : viewBeginTime.trim();
    }

    public String getViewEndTime() {
        return viewEndTime;
    }

    public void setViewEndTime(String viewEndTime) {
        this.viewEndTime = viewEndTime == null ? null : viewEndTime.trim();
    }

    public String getViewTime() {
        return viewTime;
    }

    public void setViewTime(String viewTime) {
        this.viewTime = viewTime == null ? null : viewTime.trim();
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Integer getShopDetailId() {
        return shopDetailId;
    }

    public void setShopDetailId(Integer shopDetailId) {
        this.shopDetailId = shopDetailId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Integer getFlyId() {
        return flyId;
    }

    public void setFlyId(Integer flyId) {
        this.flyId = flyId;
    }
}