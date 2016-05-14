/*
 * Probability.java
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
public class Probability {
    private Long id;

    /**
    /*关联用户表id
    */
    private String userid;

    /**
    /*概率表Id
    */
    private String questionid;

    private Integer probability3;

    private Integer probability7;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid == null ? null : questionid.trim();
    }

    public Integer getProbability3() {
        return probability3;
    }

    public void setProbability3(Integer probability3) {
        this.probability3 = probability3;
    }

    public Integer getProbability7() {
        return probability7;
    }

    public void setProbability7(Integer probability7) {
        this.probability7 = probability7;
    }
}