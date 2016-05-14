/*
 * LearningAnalysis.java
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
public class LearningAnalysis {
    /**
    /*学情分析id
    */
    private Integer learningAnalysisId;

    private String learningAnalysisName;

    /**
    /*做题数量
    */
    private Integer doSubjectNumber;

    /**
    /* 做题错误次数
    */
    private Integer doSubjectErrorNumber;

    /**
    /*错误率
    */
    private Integer errorRate;

    /**
    /*学习次数
    */
    private Integer learnNumber;

    /**
    /*学习时长
    */
    private String learnShowTime;

    public Integer getLearningAnalysisId() {
        return learningAnalysisId;
    }

    public void setLearningAnalysisId(Integer learningAnalysisId) {
        this.learningAnalysisId = learningAnalysisId;
    }

    public String getLearningAnalysisName() {
        return learningAnalysisName;
    }

    public void setLearningAnalysisName(String learningAnalysisName) {
        this.learningAnalysisName = learningAnalysisName == null ? null : learningAnalysisName.trim();
    }

    public Integer getDoSubjectNumber() {
        return doSubjectNumber;
    }

    public void setDoSubjectNumber(Integer doSubjectNumber) {
        this.doSubjectNumber = doSubjectNumber;
    }

    public Integer getDoSubjectErrorNumber() {
        return doSubjectErrorNumber;
    }

    public void setDoSubjectErrorNumber(Integer doSubjectErrorNumber) {
        this.doSubjectErrorNumber = doSubjectErrorNumber;
    }

    public Integer getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(Integer errorRate) {
        this.errorRate = errorRate;
    }

    public Integer getLearnNumber() {
        return learnNumber;
    }

    public void setLearnNumber(Integer learnNumber) {
        this.learnNumber = learnNumber;
    }

    public String getLearnShowTime() {
        return learnShowTime;
    }

    public void setLearnShowTime(String learnShowTime) {
        this.learnShowTime = learnShowTime == null ? null : learnShowTime.trim();
    }
}