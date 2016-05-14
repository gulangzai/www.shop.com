/*
 * KnowledgePoint.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 知识点表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class KnowledgePoint {
    /**
    /*知识点id
    */
    private Integer pointId;

    private String chapterId;

    private String sectionId;

    /**
    /*知识点内容
    */
    private String pointContent;

    /**
    /*知识点学习顺序
    */
    private Integer contentOrder;

    /**
    /*解析
    */
    private String explain;

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
    /*2015年是否考核
    */
    private Integer isAssess;

    /**
    /*2015年分值
    */
    private String score;

    /**
    /*“出题形式”：包含“单选题”、“多选题”、“案例题”三个选项，填写时，仅填写数字符号，比如，单选题数字代表：1，多选题数字代表:2；案例题数字代表：3，该标签为多选，中间用“，”分开
    */
    private String topicForm;

    /**
    /*学习要求（包含“记忆”、“理解”、“计算/应用”三个选项，填写时，仅填写数字符号，比如，记忆数字符号：1，理解数字代表:2；计算/应用的数字代表：3，该标签为多选，中间用“，”分开)
    */
    private String studyRequire;

    /**
    /*考试次数
    */
    private Integer examNumber;

    /**
    /*考试频率(百分比）
    */
    private Integer examFrequency;

    /**
    /*唯一学习要求学习要求（包含“记忆”、“理解”、“计算/应用”三个选项，填写时，仅填写数字符号，比如，记忆数字符号：1，理解数字代表:2；计算/应用的数字代表：3，该标签为多选；0：基础数据)
    */
    private Integer maxStudyRequire;

    /**
    /*视频总时长
    */
    private Integer videoTotalTime;

    /**
    /*视频名称
    */
    private String videoName;

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
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

    public String getPointContent() {
        return pointContent;
    }

    public void setPointContent(String pointContent) {
        this.pointContent = pointContent == null ? null : pointContent.trim();
    }

    public Integer getContentOrder() {
        return contentOrder;
    }

    public void setContentOrder(Integer contentOrder) {
        this.contentOrder = contentOrder;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
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

    public Integer getMaxStudyRequire() {
        return maxStudyRequire;
    }

    public void setMaxStudyRequire(Integer maxStudyRequire) {
        this.maxStudyRequire = maxStudyRequire;
    }

    public Integer getVideoTotalTime() {
        return videoTotalTime;
    }

    public void setVideoTotalTime(Integer videoTotalTime) {
        this.videoTotalTime = videoTotalTime;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName == null ? null : videoName.trim();
    }
}