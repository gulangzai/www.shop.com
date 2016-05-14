/*
 * CourseCollect.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 课件集
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class CourseCollect {
    /**
    /*课件集（地址）id
    */
    private String collectId;

    /**
    /*所属班级
    */
    private String classesId;

    /**
    /*课件集名称
    */
    private String courseCollectName;

    /**
    /*所属专业
    */
    private String majorId;

    /**
    /*是否删除（0:未删除 1：删除）
    */
    private Integer isDel;

    /**
    /*所属科目
    */
    private String subjectId;

    /**
    /*课件前言
    */
    private String foreword;

    /**
    /*阶段
    */
    private Integer stage;

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId == null ? null : collectId.trim();
    }

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId == null ? null : classesId.trim();
    }

    public String getCourseCollectName() {
        return courseCollectName;
    }

    public void setCourseCollectName(String courseCollectName) {
        this.courseCollectName = courseCollectName == null ? null : courseCollectName.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getForeword() {
        return foreword;
    }

    public void setForeword(String foreword) {
        this.foreword = foreword == null ? null : foreword.trim();
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }
}