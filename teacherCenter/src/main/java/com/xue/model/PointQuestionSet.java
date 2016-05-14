/*
 * PointQuestionSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-10-27 Created
 */
package com.xue.model;

/**
 * 知识点题库对应表 原表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-10-27
 */
public class PointQuestionSet {
    private Integer id;

    private Integer pointId;

    private Integer questionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
}