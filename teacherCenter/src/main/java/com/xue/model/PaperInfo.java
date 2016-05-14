/*
 * Paperinfo.java
 * Copyright(C) 2015-2018 学尔�?
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

import java.util.Date;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class PaperInfo {
    private Integer paperid;

    /**
    /*试卷标题
    */
    private String papername;

    /**
    /*试卷类型
    */
    private String papertype;

    /**
    /*试卷总分
    */
    private Integer totalnum;

    /**
    /*题目数量
    */
    private Integer questionnum;

    /**
    /*考试时间
    */
    private Integer anwsertime;

    /**
    /*试卷类型名称
    */
    private String typename;

    /**
    /*单�?总题�?
    */
    private Integer radiosum;

    /**
    /*多�?总题�?
    */
    private Integer checksum;

    /**
    /*问答总题�?
    */
    private Integer questionsum;

    /**
    /*(0:月月考，1：全国模考賽�?：在线模�?
    */
    private Integer source;

    /**
    /*问答总分
    */
    private Integer questiontotalpoints;

    /**
    /*考试时间
    */
    private Date examtime;

    /**
    /*是否存在直播(0:是，1：否)
    */
    private Integer islive;

    /**
    /*直播时间
    */
    private Date livetime;

    /**
    /*月月考月�?
    */
    private Integer monthonmonth;

    private String subjectId;

    private String majorId;

    public Integer getPaperid() {
        return paperid;
    }

    public void setPaperid(Integer paperid) {
        this.paperid = paperid;
    }

    public String getPapername() {
        return papername;
    }

    public void setPapername(String papername) {
        this.papername = papername == null ? null : papername.trim();
    }

    public String getPapertype() {
        return papertype;
    }

    public void setPapertype(String papertype) {
        this.papertype = papertype == null ? null : papertype.trim();
    }

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    public Integer getQuestionnum() {
        return questionnum;
    }

    public void setQuestionnum(Integer questionnum) {
        this.questionnum = questionnum;
    }

    public Integer getAnwsertime() {
        return anwsertime;
    }

    public void setAnwsertime(Integer anwsertime) {
        this.anwsertime = anwsertime;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public Integer getRadiosum() {
        return radiosum;
    }

    public void setRadiosum(Integer radiosum) {
        this.radiosum = radiosum;
    }

    public Integer getChecksum() {
        return checksum;
    }

    public void setChecksum(Integer checksum) {
        this.checksum = checksum;
    }

    public Integer getQuestionsum() {
        return questionsum;
    }

    public void setQuestionsum(Integer questionsum) {
        this.questionsum = questionsum;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getQuestiontotalpoints() {
        return questiontotalpoints;
    }

    public void setQuestiontotalpoints(Integer questiontotalpoints) {
        this.questiontotalpoints = questiontotalpoints;
    }

    public Date getExamtime() {
        return examtime;
    }

    public void setExamtime(Date examtime) {
        this.examtime = examtime;
    }

    public Integer getIslive() {
        return islive;
    }

    public void setIslive(Integer islive) {
        this.islive = islive;
    }

    public Date getLivetime() {
        return livetime;
    }

    public void setLivetime(Date livetime) {
        this.livetime = livetime;
    }

    public Integer getMonthonmonth() {
        return monthonmonth;
    }

    public void setMonthonmonth(Integer monthonmonth) {
        this.monthonmonth = monthonmonth;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }
}