/*
 * BsStudentCallbackSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 学员回访记录关联表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class BsStudentCallbackSet {
    private Integer id;

    private String studentId;

    private Integer callbackId;

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

    public Integer getCallbackId() {
        return callbackId;
    }

    public void setCallbackId(Integer callbackId) {
        this.callbackId = callbackId;
    }
}