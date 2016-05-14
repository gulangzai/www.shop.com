/*
 * TopicSet.java
 * Copyright(C) 2015-2018 学尔森
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
public class TopicSet {
    /**
    /*题库集id
    */
    private String topicSetId;

    /**
    /*题库集名称
    */
    private String topicSetName;

    /**
    /*专业id
    */
    private String majorId;

    /**
    /*科目id
    */
    private String subjectId;

    /**
    /*习题状态
    */
    private String disabledFlag;

    /**
    /*组卷模考单选题数
    */
    private Integer singeCount;

    /**
    /*组卷模考单选题分值
    */
    private Integer singeScore;

    /**
    /*组卷模考多选题数
    */
    private Integer mutiCount;

    /**
    /*组卷模考多选题分值
    */
    private Integer mutiScore;

    /**
    /*组卷模考多选题数
    */
    private Integer analysisCount;

    /**
    /*组卷模考多选题分值
    */
    private Integer analysisScore;

    /**
    /*创建时间
    */
    private Date createDate;

    /**
    /*是否删除（0:未删除 1：删除）
    */
    private String isDel;

    public String getTopicSetId() {
        return topicSetId;
    }

    public void setTopicSetId(String topicSetId) {
        this.topicSetId = topicSetId == null ? null : topicSetId.trim();
    }

    public String getTopicSetName() {
        return topicSetName;
    }

    public void setTopicSetName(String topicSetName) {
        this.topicSetName = topicSetName == null ? null : topicSetName.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getDisabledFlag() {
        return disabledFlag;
    }

    public void setDisabledFlag(String disabledFlag) {
        this.disabledFlag = disabledFlag == null ? null : disabledFlag.trim();
    }

    public Integer getSingeCount() {
        return singeCount;
    }

    public void setSingeCount(Integer singeCount) {
        this.singeCount = singeCount;
    }

    public Integer getSingeScore() {
        return singeScore;
    }

    public void setSingeScore(Integer singeScore) {
        this.singeScore = singeScore;
    }

    public Integer getMutiCount() {
        return mutiCount;
    }

    public void setMutiCount(Integer mutiCount) {
        this.mutiCount = mutiCount;
    }

    public Integer getMutiScore() {
        return mutiScore;
    }

    public void setMutiScore(Integer mutiScore) {
        this.mutiScore = mutiScore;
    }

    public Integer getAnalysisCount() {
        return analysisCount;
    }

    public void setAnalysisCount(Integer analysisCount) {
        this.analysisCount = analysisCount;
    }

    public Integer getAnalysisScore() {
        return analysisScore;
    }

    public void setAnalysisScore(Integer analysisScore) {
        this.analysisScore = analysisScore;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }
}