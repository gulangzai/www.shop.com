/*
 * LiveSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 直播集
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class LiveSet {
    /**
    /*直播集id
    */
    private String liveSetId;

    /**
    /*直播集名称
    */
    private String liveSetName;

    /**
    /*是否删除（0:免费    1：收费）
    */
    private Integer isDel;

    /**
    /*课程id
    */
    private String courseId;

    /**
    /*专业id
    */
    private String majorId;

    /**
    /*科目id
    */
    private String subjectId;

    public String getLiveSetId() {
        return liveSetId;
    }

    public void setLiveSetId(String liveSetId) {
        this.liveSetId = liveSetId == null ? null : liveSetId.trim();
    }

    public String getLiveSetName() {
        return liveSetName;
    }

    public void setLiveSetName(String liveSetName) {
        this.liveSetName = liveSetName == null ? null : liveSetName.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }
}