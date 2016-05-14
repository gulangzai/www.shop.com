/*
 * StudentPlanPhase.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 学员计划科目阶段表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class StudentPlanPhase {
    /**
    /*计划科目阶段表
    */
    private Integer phaseSubjectId;

    /**
    /*科目名称
    */
    private String subjectName;

    /**
    /*阶段顺序
    */
    private Integer phaseOrder;

    /**
    /*知识点课程已学习次数
    */
    private Integer pointBeginNumber;

    /**
    /*知识点学习总次数
    */
    private Integer pointEndNumber;

    /**
    /*课程作业已完成数
    */
    private Integer courseBeginNumber;

    /**
    /*课程作业总数
    */
    private Integer courseEndNumber;

    /**
    /*错点已完成数
    */
    private Integer wrongPointBeginNumber;

    /**
    /*错点总数
    */
    private Integer wrongPointEndNumber;

    /**
    /*阶段考试已完成数
    */
    private Integer phaseExamBeginNumber;

    /**
    /*阶段考试总数量
    */
    private Integer phaseExamEndNumber;

    /**
    /*直播答疑已完成数
    */
    private Integer liveBeginNumber;

    /**
    /*直播答疑总数
    */
    private Integer liveEndNumber;

    private String subjectId;

    /**
    /*是否生成知识点(0：否,1：是)
    */
    private Integer isGenerateKnowledge;

    /**
    /*专业
    */
    private String majorId;

    public Integer getPhaseSubjectId() {
        return phaseSubjectId;
    }

    public void setPhaseSubjectId(Integer phaseSubjectId) {
        this.phaseSubjectId = phaseSubjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public Integer getPhaseOrder() {
        return phaseOrder;
    }

    public void setPhaseOrder(Integer phaseOrder) {
        this.phaseOrder = phaseOrder;
    }

    public Integer getPointBeginNumber() {
        return pointBeginNumber;
    }

    public void setPointBeginNumber(Integer pointBeginNumber) {
        this.pointBeginNumber = pointBeginNumber;
    }

    public Integer getPointEndNumber() {
        return pointEndNumber;
    }

    public void setPointEndNumber(Integer pointEndNumber) {
        this.pointEndNumber = pointEndNumber;
    }

    public Integer getCourseBeginNumber() {
        return courseBeginNumber;
    }

    public void setCourseBeginNumber(Integer courseBeginNumber) {
        this.courseBeginNumber = courseBeginNumber;
    }

    public Integer getCourseEndNumber() {
        return courseEndNumber;
    }

    public void setCourseEndNumber(Integer courseEndNumber) {
        this.courseEndNumber = courseEndNumber;
    }

    public Integer getWrongPointBeginNumber() {
        return wrongPointBeginNumber;
    }

    public void setWrongPointBeginNumber(Integer wrongPointBeginNumber) {
        this.wrongPointBeginNumber = wrongPointBeginNumber;
    }

    public Integer getWrongPointEndNumber() {
        return wrongPointEndNumber;
    }

    public void setWrongPointEndNumber(Integer wrongPointEndNumber) {
        this.wrongPointEndNumber = wrongPointEndNumber;
    }

    public Integer getPhaseExamBeginNumber() {
        return phaseExamBeginNumber;
    }

    public void setPhaseExamBeginNumber(Integer phaseExamBeginNumber) {
        this.phaseExamBeginNumber = phaseExamBeginNumber;
    }

    public Integer getPhaseExamEndNumber() {
        return phaseExamEndNumber;
    }

    public void setPhaseExamEndNumber(Integer phaseExamEndNumber) {
        this.phaseExamEndNumber = phaseExamEndNumber;
    }

    public Integer getLiveBeginNumber() {
        return liveBeginNumber;
    }

    public void setLiveBeginNumber(Integer liveBeginNumber) {
        this.liveBeginNumber = liveBeginNumber;
    }

    public Integer getLiveEndNumber() {
        return liveEndNumber;
    }

    public void setLiveEndNumber(Integer liveEndNumber) {
        this.liveEndNumber = liveEndNumber;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public Integer getIsGenerateKnowledge() {
        return isGenerateKnowledge;
    }

    public void setIsGenerateKnowledge(Integer isGenerateKnowledge) {
        this.isGenerateKnowledge = isGenerateKnowledge;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }
}