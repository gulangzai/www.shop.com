/*
 * MajorClassesSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 专业班级关联表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class MajorClassesSet {
    /**
    /*所属专业
    */
    private String majorId;

    /**
    /*所属班级
    */
    private String classesId;

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId == null ? null : classesId.trim();
    }
}