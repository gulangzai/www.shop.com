/*
 * StudentDetailPointViewMoive.java
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
public class StudentDetailPointViewMoive {
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
}