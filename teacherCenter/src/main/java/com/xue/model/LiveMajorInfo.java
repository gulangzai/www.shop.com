/*
 * LiveMajorInfo.java
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
public class LiveMajorInfo {
    private Integer liveMajorId;

    /**
    /*直播专业名称
    */
    private String liveMajorName;

    /**
    /*专业类型（1.智能网课专业。2.直播网课专业）
    */
    private Integer majorType;

    /**
    /*授课教师
    */
    private String teacher;

    /**
    /*备注
    */
    private String remarks;

    /**
    /*课程有效期
    */
    private String courseValidity;

    /**
    /*播放次数
    */
    private Integer playNumber;

    /**
    /*评价次数
    */
    private Integer evaluateNumber;

    /**
    /*学习时长
    */
    private String learnTime;

    /**
    /*报名人数
    */
    private Integer studyNumber;

    /**
    /*图片路径
    */
    private String liveMajorPicture;

    public Integer getLiveMajorId() {
        return liveMajorId;
    }

    public void setLiveMajorId(Integer liveMajorId) {
        this.liveMajorId = liveMajorId;
    }

    public String getLiveMajorName() {
        return liveMajorName;
    }

    public void setLiveMajorName(String liveMajorName) {
        this.liveMajorName = liveMajorName == null ? null : liveMajorName.trim();
    }

    public Integer getMajorType() {
        return majorType;
    }

    public void setMajorType(Integer majorType) {
        this.majorType = majorType;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher == null ? null : teacher.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getCourseValidity() {
        return courseValidity;
    }

    public void setCourseValidity(String courseValidity) {
        this.courseValidity = courseValidity == null ? null : courseValidity.trim();
    }

    public Integer getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(Integer playNumber) {
        this.playNumber = playNumber;
    }

    public Integer getEvaluateNumber() {
        return evaluateNumber;
    }

    public void setEvaluateNumber(Integer evaluateNumber) {
        this.evaluateNumber = evaluateNumber;
    }

    public String getLearnTime() {
        return learnTime;
    }

    public void setLearnTime(String learnTime) {
        this.learnTime = learnTime == null ? null : learnTime.trim();
    }

    public Integer getStudyNumber() {
        return studyNumber;
    }

    public void setStudyNumber(Integer studyNumber) {
        this.studyNumber = studyNumber;
    }

    public String getLiveMajorPicture() {
        return liveMajorPicture;
    }

    public void setLiveMajorPicture(String liveMajorPicture) {
        this.liveMajorPicture = liveMajorPicture == null ? null : liveMajorPicture.trim();
    }
}