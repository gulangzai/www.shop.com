/*
 * StudentPlayHistory.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

import java.util.Date;

/**
 * 学生视频播放记录信息
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class StudentPlayHistory {
    /**
    /*观看记录id
    */
    private String playHistoryId;

    /**
    /*所看课件id
    */
    private String courseId;

    /**
    /*当前观看系统时间
    */
    private Date lookDate;

    /**
    /*已看时长
    */
    private Float loookedTime;

    /**
    /*是否删除（0:未删除    1：删除）
    */
    private String isDel;

    /**
    /*学员id
    */
    private String studentId;

    public String getPlayHistoryId() {
        return playHistoryId;
    }

    public void setPlayHistoryId(String playHistoryId) {
        this.playHistoryId = playHistoryId == null ? null : playHistoryId.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public Date getLookDate() {
        return lookDate;
    }

    public void setLookDate(Date lookDate) {
        this.lookDate = lookDate;
    }

    public Float getLoookedTime() {
        return loookedTime;
    }

    public void setLoookedTime(Float loookedTime) {
        this.loookedTime = loookedTime;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }
}