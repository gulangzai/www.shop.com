/*
 * StudentExamInfo.java
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
public class StudentExamInfo {
    /**
    /*id
    */
    private String examId;

    /**
    /*所考科目
    */
    private String examSubject;

    /**
    /*开始时间
    */
    private Date examBeginTime;

    /**
    /*结束时间
    */
    private Date examEndTime;

    /**
    /*考试用时
    */
    private String examTotalTime;

    /**
    /*考试成绩
    */
    private String examResult;

    /**
    /*试卷题数
    */
    private Integer questNum;

    /**
    /*正确题数
    */
    private Integer rightNum;

    /**
    /*试卷得分
    */
    private Float score;

    /**
    /*总分
    */
    private Integer allScore;

    /**
    /*名次
    */
    private Integer ranking;

    /**
    /*打败了多少人
    */
    private Integer beat;

    /**
    /*暂时废弃该字段
    */
    private Integer status;

    /**
    /*多选获得分数
    */
    private Float multipleScore;

    /**
    /*单选获得分数
    */
    private Float radioScore;

    /**
    /*简答题获得分数
    */
    private Float subjectiveScore;

    /**
    /*关联paper表的主键
    */
    private Integer paperId;

    /**
    /*数据来源类型（1.月月考，2全国模考赛，3在线模考，4……）
    */
    private Integer sourceType;

    private String subjectId;

    private String majorId;

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId == null ? null : examId.trim();
    }

    public String getExamSubject() {
        return examSubject;
    }

    public void setExamSubject(String examSubject) {
        this.examSubject = examSubject == null ? null : examSubject.trim();
    }

    public Date getExamBeginTime() {
        return examBeginTime;
    }

    public void setExamBeginTime(Date examBeginTime) {
        this.examBeginTime = examBeginTime;
    }

    public Date getExamEndTime() {
        return examEndTime;
    }

    public void setExamEndTime(Date examEndTime) {
        this.examEndTime = examEndTime;
    }

    public String getExamTotalTime() {
        return examTotalTime;
    }

    public void setExamTotalTime(String examTotalTime) {
        this.examTotalTime = examTotalTime == null ? null : examTotalTime.trim();
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult == null ? null : examResult.trim();
    }

    public Integer getQuestNum() {
        return questNum;
    }

    public void setQuestNum(Integer questNum) {
        this.questNum = questNum;
    }

    public Integer getRightNum() {
        return rightNum;
    }

    public void setRightNum(Integer rightNum) {
        this.rightNum = rightNum;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getAllScore() {
        return allScore;
    }

    public void setAllScore(Integer allScore) {
        this.allScore = allScore;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getBeat() {
        return beat;
    }

    public void setBeat(Integer beat) {
        this.beat = beat;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Float getMultipleScore() {
        return multipleScore;
    }

    public void setMultipleScore(Float multipleScore) {
        this.multipleScore = multipleScore;
    }

    public Float getRadioScore() {
        return radioScore;
    }

    public void setRadioScore(Float radioScore) {
        this.radioScore = radioScore;
    }

    public Float getSubjectiveScore() {
        return subjectiveScore;
    }

    public void setSubjectiveScore(Float subjectiveScore) {
        this.subjectiveScore = subjectiveScore;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
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