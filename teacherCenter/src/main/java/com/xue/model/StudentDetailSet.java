/*
 * StudentDetailSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 学员和学员详情关联表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class StudentDetailSet {
    private Long id;

    private String studentid;

    private Long studentdetailid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid == null ? null : studentid.trim();
    }

    public Long getStudentdetailid() {
        return studentdetailid;
    }

    public void setStudentdetailid(Long studentdetailid) {
        this.studentdetailid = studentdetailid;
    }
}