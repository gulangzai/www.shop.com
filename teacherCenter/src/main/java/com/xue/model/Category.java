/*
 * Category.java
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
public class Category {
    /**
    /*科目类别Id
    */
    private String categoryid;

    /**
    /*科目类别名称
    */
    private String categoryname;

    /**
    /*关联CategoryId
    */
    private String parentid;

    /**
    /*题目内容
    */
    private String subjectname;

    /**
    /*正确答案
    */
    private String rightanswers;

    /**
    /*题目解释
    */
    private String explain;

    /**
    /*关联科目id
    */
    private String courseid;

    /**
    /*题目选择项（A:XXX;B:XXX;等等）注：取数据时，根据CategoryId到ParentId获取选择项
    */
    private String subjectoptiona;

    private String subjectoptionb;

    private String subjectoptionc;

    private String subjectoptiond;

    private String subjectoptione;

    /**
    /*题目类型（1.单选；2.多选）
    */
    private Integer questiontypes;

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid == null ? null : categoryid.trim();
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname == null ? null : subjectname.trim();
    }

    public String getRightanswers() {
        return rightanswers;
    }

    public void setRightanswers(String rightanswers) {
        this.rightanswers = rightanswers == null ? null : rightanswers.trim();
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid == null ? null : courseid.trim();
    }

    public String getSubjectoptiona() {
        return subjectoptiona;
    }

    public void setSubjectoptiona(String subjectoptiona) {
        this.subjectoptiona = subjectoptiona == null ? null : subjectoptiona.trim();
    }

    public String getSubjectoptionb() {
        return subjectoptionb;
    }

    public void setSubjectoptionb(String subjectoptionb) {
        this.subjectoptionb = subjectoptionb == null ? null : subjectoptionb.trim();
    }

    public String getSubjectoptionc() {
        return subjectoptionc;
    }

    public void setSubjectoptionc(String subjectoptionc) {
        this.subjectoptionc = subjectoptionc == null ? null : subjectoptionc.trim();
    }

    public String getSubjectoptiond() {
        return subjectoptiond;
    }

    public void setSubjectoptiond(String subjectoptiond) {
        this.subjectoptiond = subjectoptiond == null ? null : subjectoptiond.trim();
    }

    public String getSubjectoptione() {
        return subjectoptione;
    }

    public void setSubjectoptione(String subjectoptione) {
        this.subjectoptione = subjectoptione == null ? null : subjectoptione.trim();
    }

    public Integer getQuestiontypes() {
        return questiontypes;
    }

    public void setQuestiontypes(Integer questiontypes) {
        this.questiontypes = questiontypes;
    }
}