/*
 * StudentExamSet.java
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
public class StudentExamSet {
    private Integer id;

    /**
    /*学员id
    */
    private String studentId;

    /**
    /*考试id
    */
    private String examId;

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

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId == null ? null : examId.trim();
    }
}