/*
 * StudentQuestionsAnswersLabelSet.java
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
public class StudentQuestionsAnswersLabelSet {
    private Integer id;

    /**
    /*我的问题id
    */
    private Integer questionsAnswersId;

    /**
    /*标签id
    */
    private Integer labelId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionsAnswersId() {
        return questionsAnswersId;
    }

    public void setQuestionsAnswersId(Integer questionsAnswersId) {
        this.questionsAnswersId = questionsAnswersId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }
}