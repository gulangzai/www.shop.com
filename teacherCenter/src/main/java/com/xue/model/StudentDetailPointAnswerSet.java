/*
 * StudentDetailPointAnswerSet.java
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
public class StudentDetailPointAnswerSet {
    private Integer id;

    private Integer detailPointId;

    private Integer answerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDetailPointId() {
        return detailPointId;
    }

    public void setDetailPointId(Integer detailPointId) {
        this.detailPointId = detailPointId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }
}