/*
 * Entityquestion.java
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
public class Entityquestion {
    /**
    /*试卷ID 对应student_exam_info的主键exam_id
    */
    private String processaid;

    /**
    /*该题得分
    */
    private Integer score;

    /**
    /*该题目总分
    */
    private Integer total;

    /**
    /*是否答对 1对 0错
    */
    private String isright;

    /**
    /*题目类型，1单选，2多选，3案例
    */
    private Integer topicType;

    private String selTextf;

    private String selTexte;

    private String selTextd;

    private String selTextc;

    private String selTextb;

    /**
    /*简答题的回答A
    */
    private String selTexta;

    private String selAnswer;

    /**
    /*是否观看知识点视频
    */
    private Integer isViewMoive;

    /**
    /*是否选中0默认未选中，1选中押题率30%，2选中押题率70%
    */
    private Integer probability;

    /**
    /*题目ID 关联questionInfo 
    */
    private Integer questionid;

    public String getProcessaid() {
        return processaid;
    }

    public void setProcessaid(String processaid) {
        this.processaid = processaid == null ? null : processaid.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getIsright() {
        return isright;
    }

    public void setIsright(String isright) {
        this.isright = isright == null ? null : isright.trim();
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public String getSelTextf() {
        return selTextf;
    }

    public void setSelTextf(String selTextf) {
        this.selTextf = selTextf == null ? null : selTextf.trim();
    }

    public String getSelTexte() {
        return selTexte;
    }

    public void setSelTexte(String selTexte) {
        this.selTexte = selTexte == null ? null : selTexte.trim();
    }

    public String getSelTextd() {
        return selTextd;
    }

    public void setSelTextd(String selTextd) {
        this.selTextd = selTextd == null ? null : selTextd.trim();
    }

    public String getSelTextc() {
        return selTextc;
    }

    public void setSelTextc(String selTextc) {
        this.selTextc = selTextc == null ? null : selTextc.trim();
    }

    public String getSelTextb() {
        return selTextb;
    }

    public void setSelTextb(String selTextb) {
        this.selTextb = selTextb == null ? null : selTextb.trim();
    }

    public String getSelTexta() {
        return selTexta;
    }

    public void setSelTexta(String selTexta) {
        this.selTexta = selTexta == null ? null : selTexta.trim();
    }

    public String getSelAnswer() {
        return selAnswer;
    }

    public void setSelAnswer(String selAnswer) {
        this.selAnswer = selAnswer == null ? null : selAnswer.trim();
    }

    public Integer getIsViewMoive() {
        return isViewMoive;
    }

    public void setIsViewMoive(Integer isViewMoive) {
        this.isViewMoive = isViewMoive;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }
}