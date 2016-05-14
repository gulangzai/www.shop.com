/*
 * StudentDataSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 学员信息资料关联表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class StudentDataSet {
    private Integer id;

    /**
    /*学员id
    */
    private String studentId;

    /**
    /*学员资料id
    */
    private Integer studentDataId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Integer getStudentDataId() {
        return studentDataId;
    }

    public void setStudentDataId(Integer studentDataId) {
        this.studentDataId = studentDataId;
    }
}