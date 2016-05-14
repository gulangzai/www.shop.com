/*
 * PaperStudentSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 试卷与学员id 中间表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class PaperStudentSet {
    private Long id;

    /**
    /*学员id
    */
    private String studentid;

    /**
    /*试卷id
    */
    private Integer paperid;

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

    public Integer getPaperid() {
        return paperid;
    }

    public void setPaperid(Integer paperid) {
        this.paperid = paperid;
    }
}