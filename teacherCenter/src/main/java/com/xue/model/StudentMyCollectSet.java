/*
 * StudentMyCollectSet.java
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
public class StudentMyCollectSet {
    /**
    /*id
    */
    private Integer id;

    /**
    /*所属学院id
    */
    private String studentId;

    /**
    /*所属我的收藏夹id
    */
    private Integer myCollectId;

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

    public Integer getMyCollectId() {
        return myCollectId;
    }

    public void setMyCollectId(Integer myCollectId) {
        this.myCollectId = myCollectId;
    }
}