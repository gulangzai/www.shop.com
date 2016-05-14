/*
 * Topic.java
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
public class Topic {
    /**
    /*题目id
    */
    private String topicId;

    /**
    /*题库id
    */
    private String topicseId;

    /**
    /*题目类型
    */
    private String topictype;

    /**
    /*题目序号
    */
    private Integer sortnumber;

    /**
    /*习题内容
    */
    private String question;

    /**
    /*解析
    */
    private String explain;

    /**
    /*多选题时，答案以","分割
    */
    private String corectanswer;

    /**
    /*难易程度（0.1;0.2……）
    */
    private Float difficulty;

    /**
    /*单选题和多选题时使用
    */
    private String selectA;

    /**
    /*单选题和多选题时使用
    */
    private String selectB;

    /**
    /*单选题和多选题时使用
    */
    private String selectC;

    /**
    /*单选题和多选题时使用
    */
    private String selectD;

    /**
    /*单选题和多选题时使用
    */
    private String selectE;

    /**
    /*单选题和多选题时使用
    */
    private String selectF;

    /**
    /*单选题和多选题时使用
    */
    private String selectG;

    /**
    /*视频链接
    */
    private String liveLink;

    /**
    /*是否是真题（0:否 1：是）
    */
    private String isRealFlag;

    /**
    /*习题状态（0:正常 1：停用）
    */
    private String disabledFlag;

    /**
    /*是否删除（0:未删除 1：删除）
    */
    private String isDel;

    private String topicname;

    /**
    /*年度
    */
    private String testperiod;

    /**
    /*提示
    */
    private String topicPrompt;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId == null ? null : topicId.trim();
    }

    public String getTopicseId() {
        return topicseId;
    }

    public void setTopicseId(String topicseId) {
        this.topicseId = topicseId == null ? null : topicseId.trim();
    }

    public String getTopictype() {
        return topictype;
    }

    public void setTopictype(String topictype) {
        this.topictype = topictype == null ? null : topictype.trim();
    }

    public Integer getSortnumber() {
        return sortnumber;
    }

    public void setSortnumber(Integer sortnumber) {
        this.sortnumber = sortnumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }

    public String getCorectanswer() {
        return corectanswer;
    }

    public void setCorectanswer(String corectanswer) {
        this.corectanswer = corectanswer == null ? null : corectanswer.trim();
    }

    public Float getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Float difficulty) {
        this.difficulty = difficulty;
    }

    public String getSelectA() {
        return selectA;
    }

    public void setSelectA(String selectA) {
        this.selectA = selectA == null ? null : selectA.trim();
    }

    public String getSelectB() {
        return selectB;
    }

    public void setSelectB(String selectB) {
        this.selectB = selectB == null ? null : selectB.trim();
    }

    public String getSelectC() {
        return selectC;
    }

    public void setSelectC(String selectC) {
        this.selectC = selectC == null ? null : selectC.trim();
    }

    public String getSelectD() {
        return selectD;
    }

    public void setSelectD(String selectD) {
        this.selectD = selectD == null ? null : selectD.trim();
    }

    public String getSelectE() {
        return selectE;
    }

    public void setSelectE(String selectE) {
        this.selectE = selectE == null ? null : selectE.trim();
    }

    public String getSelectF() {
        return selectF;
    }

    public void setSelectF(String selectF) {
        this.selectF = selectF == null ? null : selectF.trim();
    }

    public String getSelectG() {
        return selectG;
    }

    public void setSelectG(String selectG) {
        this.selectG = selectG == null ? null : selectG.trim();
    }

    public String getLiveLink() {
        return liveLink;
    }

    public void setLiveLink(String liveLink) {
        this.liveLink = liveLink == null ? null : liveLink.trim();
    }

    public String getIsRealFlag() {
        return isRealFlag;
    }

    public void setIsRealFlag(String isRealFlag) {
        this.isRealFlag = isRealFlag == null ? null : isRealFlag.trim();
    }

    public String getDisabledFlag() {
        return disabledFlag;
    }

    public void setDisabledFlag(String disabledFlag) {
        this.disabledFlag = disabledFlag == null ? null : disabledFlag.trim();
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname == null ? null : topicname.trim();
    }

    public String getTestperiod() {
        return testperiod;
    }

    public void setTestperiod(String testperiod) {
        this.testperiod = testperiod == null ? null : testperiod.trim();
    }

    public String getTopicPrompt() {
        return topicPrompt;
    }

    public void setTopicPrompt(String topicPrompt) {
        this.topicPrompt = topicPrompt == null ? null : topicPrompt.trim();
    }
}