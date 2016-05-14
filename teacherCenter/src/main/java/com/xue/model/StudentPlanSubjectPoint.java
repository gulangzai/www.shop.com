/*
 * StudentPlanSubjectPoint.java
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
public class StudentPlanSubjectPoint {
    /**
    /*计划科目阶段知识点id
    */
    private Integer planPointId;

    /**
    /*上次观看时间
    */
    private Float lastTimeMoive;

    /**
    /*是否掌握(0 : 未学，1：未掌握，2：掌握)
    */
    private Integer isGrasp;

    /**
    /*做题数量
    */
    private Integer topicNumber;

    /**
    /*错误次数
    */
    private Integer wrongNumber;

    /**
    /*错误率
    */
    private Float wrongRate;

    /**
    /*学习次数
    */
    private Integer learnNumber;

    /**
    /*学习时长
    */
    private Float learnTimeLength;

    /**
    /*是否已收藏(0 : 未收藏， 1 : 已收藏)
    */
    private Integer isCollect;

    private String chapterId;

    private String sectionId;

    /**
    /*知识点学习顺序
    */
    private Integer contentOrder;

    /**
    /*解析
    */
    private String textExplain;

    /**
    /*课件id
    */
    private String courseWareid;

    /**
    /*CC视频id
    */
    private String liveCcId;

    /**
    /*是否删除（0:未删除 1：删除）
    */
    private Integer isDel;

    /**
    /*难度等级
    */
    private Integer difficultyLevel;

    /**
    /*考试次数
    */
    private Integer examNumber;

    /**
    /*考试频率(百分比）
    */
    private Integer examFrequency;

    /**
    /*2015年是否考核
    */
    private Integer isAssess;

    /**
    /*2015年分值
    */
    private String score;

    /**
    /*2015年出题形式（1,2,3,4,5）
    */
    private String topicForm;

    /**
    /*学习要求（包含“记忆”、“理解”、“计算/应用”三个选项，填写时，仅填写数字符号，比如，记忆数字符号：1，理解数字代表:2；计算/应用的数字代表：3，该标签为多选，中间用“，”分开)
    */
    private String studyRequire;

    /**
    /*知识点内容
    */
    private String pointContent;

    /**
    /*知识点ID
    */
    private Integer pointId;

    public Integer getPlanPointId() {
        return planPointId;
    }

    public void setPlanPointId(Integer planPointId) {
        this.planPointId = planPointId;
    }

    public Float getLastTimeMoive() {
        return lastTimeMoive;
    }

    public void setLastTimeMoive(Float lastTimeMoive) {
        this.lastTimeMoive = lastTimeMoive;
    }

    public Integer getIsGrasp() {
        return isGrasp;
    }

    public void setIsGrasp(Integer isGrasp) {
        this.isGrasp = isGrasp;
    }

    public Integer getTopicNumber() {
        return topicNumber;
    }

    public void setTopicNumber(Integer topicNumber) {
        this.topicNumber = topicNumber;
    }

    public Integer getWrongNumber() {
        return wrongNumber;
    }

    public void setWrongNumber(Integer wrongNumber) {
        this.wrongNumber = wrongNumber;
    }

    public Float getWrongRate() {
        return wrongRate;
    }

    public void setWrongRate(Float wrongRate) {
        this.wrongRate = wrongRate;
    }

    public Integer getLearnNumber() {
        return learnNumber;
    }

    public void setLearnNumber(Integer learnNumber) {
        this.learnNumber = learnNumber;
    }

    public Float getLearnTimeLength() {
        return learnTimeLength;
    }

    public void setLearnTimeLength(Float learnTimeLength) {
        this.learnTimeLength = learnTimeLength;
    }

    public Integer getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Integer isCollect) {
        this.isCollect = isCollect;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId == null ? null : chapterId.trim();
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId == null ? null : sectionId.trim();
    }

    public Integer getContentOrder() {
        return contentOrder;
    }

    public void setContentOrder(Integer contentOrder) {
        this.contentOrder = contentOrder;
    }

    public String getTextExplain() {
        return textExplain;
    }

    public void setTextExplain(String textExplain) {
        this.textExplain = textExplain == null ? null : textExplain.trim();
    }

    public String getCourseWareid() {
        return courseWareid;
    }

    public void setCourseWareid(String courseWareid) {
        this.courseWareid = courseWareid == null ? null : courseWareid.trim();
    }

    public String getLiveCcId() {
        return liveCcId;
    }

    public void setLiveCcId(String liveCcId) {
        this.liveCcId = liveCcId == null ? null : liveCcId.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getExamNumber() {
        return examNumber;
    }

    public void setExamNumber(Integer examNumber) {
        this.examNumber = examNumber;
    }

    public Integer getExamFrequency() {
        return examFrequency;
    }

    public void setExamFrequency(Integer examFrequency) {
        this.examFrequency = examFrequency;
    }

    public Integer getIsAssess() {
        return isAssess;
    }

    public void setIsAssess(Integer isAssess) {
        this.isAssess = isAssess;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getTopicForm() {
        return topicForm;
    }

    public void setTopicForm(String topicForm) {
        this.topicForm = topicForm == null ? null : topicForm.trim();
    }

    public String getStudyRequire() {
        return studyRequire;
    }

    public void setStudyRequire(String studyRequire) {
        this.studyRequire = studyRequire == null ? null : studyRequire.trim();
    }

    public String getPointContent() {
        return pointContent;
    }

    public void setPointContent(String pointContent) {
        this.pointContent = pointContent == null ? null : pointContent.trim();
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }
}