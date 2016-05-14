/*
 * AnswerType.java
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
public class AnswerType {
    /**
    /*答题主id
    */
    private String answerId;

    /**
    /*答题类型
    */
    private String answerType;

    /**
    /*题库集id
    */
    private String topicSetId;

    /**
    /*学员id
    */
    private String studentId;

    /**
    /*答题总数
    */
    private Integer answerTotle;

    /**
    /*正确答对题数
    */
    private Integer rightTotle;

    /**
    /*错题数
    */
    private Integer wrongTotle;

    /**
    /*得分
    */
    private Float score;

    /**
    /*开始时间
    */
    private Date startTime;

    /**
    /*结束时间
    */
    private Date endTime;

    /**
    /*是否已批阅标示
    */
    private Integer readStatus;

    /**
    /*单选题总数
    */
    private Integer singeCount;

    /**
    /*单选题正确数
    */
    private Integer singeRight;

    /**
    /*单选题得分
    */
    private Integer singeScore;

    /**
    /*多选题总数
    */
    private Integer mutiCount;

    /**
    /*多选题正确数
    */
    private Integer mutiRight;

    /**
    /*多选题得分
    */
    private Integer mutiScore;

    /**
    /*是否删除（0:未删除 1：删除）
    */
    private Integer isDel;

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId == null ? null : answerId.trim();
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType == null ? null : answerType.trim();
    }

    public String getTopicSetId() {
        return topicSetId;
    }

    public void setTopicSetId(String topicSetId) {
        this.topicSetId = topicSetId == null ? null : topicSetId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Integer getAnswerTotle() {
        return answerTotle;
    }

    public void setAnswerTotle(Integer answerTotle) {
        this.answerTotle = answerTotle;
    }

    public Integer getRightTotle() {
        return rightTotle;
    }

    public void setRightTotle(Integer rightTotle) {
        this.rightTotle = rightTotle;
    }

    public Integer getWrongTotle() {
        return wrongTotle;
    }

    public void setWrongTotle(Integer wrongTotle) {
        this.wrongTotle = wrongTotle;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public Integer getSingeCount() {
        return singeCount;
    }

    public void setSingeCount(Integer singeCount) {
        this.singeCount = singeCount;
    }

    public Integer getSingeRight() {
        return singeRight;
    }

    public void setSingeRight(Integer singeRight) {
        this.singeRight = singeRight;
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

    public Integer getMutiRight() {
        return mutiRight;
    }

    public void setMutiRight(Integer mutiRight) {
        this.mutiRight = mutiRight;
    }

    public Integer getMutiScore() {
        return mutiScore;
    }

    public void setMutiScore(Integer mutiScore) {
        this.mutiScore = mutiScore;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}