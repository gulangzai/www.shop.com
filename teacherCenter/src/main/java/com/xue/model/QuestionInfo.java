/*
 * Questioninfo.java
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
public class QuestionInfo {
    private Integer questionid;

    /**
    /*总类（1，在线模考题。2，月月考题，3入学测试题。4，知识点题库）
    */
    private Integer contenttype;

    /**
    /*专业(1:一级建造师，2:二级建造师；3:注册消防师等等）
    */
    private Integer subject;

    /**
    /*科目类别（1：法律法规，2：施工管理，3，建工实务，4，机电实务，5，市政实务，7工程经济，100综合能力）
    */
    private Integer subjectclasses;

    /**
    /*试卷类型（1，A卷。2，B卷。3，C卷等等）
    */
    private Integer papertype;

    /**
    /*1 单选，2  多选，3问答题
    */
    private Integer questiontype;

    /**
    /*题目难度
    */
    private String questioneasy;

    /**
    /*题目内容
    */
    private String questioncontent;

    /**
    /*正确答案
    */
    private String questionanswer;

    /**
    /*题目分数
    */
    private Integer score;

    private Integer optiontype;

    private String answera;

    private String answerb;

    private String answerc;

    private String answerd;

    private String answere;

    private String answerf;

    /**
    /*标题
    */
    private String title;

    /**
    /*答案解析
    */
    private String questionanalysis;

    /**
    /*图片路径
    */
    private String imgpath;

    /**
    /*知识点
    */
    private String lorepoint;

    private String lorepointt;

    private String lorepointth;

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public Integer getContenttype() {
        return contenttype;
    }

    public void setContenttype(Integer contenttype) {
        this.contenttype = contenttype;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public Integer getSubjectclasses() {
        return subjectclasses;
    }

    public void setSubjectclasses(Integer subjectclasses) {
        this.subjectclasses = subjectclasses;
    }

    public Integer getPapertype() {
        return papertype;
    }

    public void setPapertype(Integer papertype) {
        this.papertype = papertype;
    }

    public Integer getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(Integer questiontype) {
        this.questiontype = questiontype;
    }

    public String getQuestioneasy() {
        return questioneasy;
    }

    public void setQuestioneasy(String questioneasy) {
        this.questioneasy = questioneasy == null ? null : questioneasy.trim();
    }

    public String getQuestioncontent() {
        return questioncontent;
    }

    public void setQuestioncontent(String questioncontent) {
        this.questioncontent = questioncontent == null ? null : questioncontent.trim();
    }

    public String getQuestionanswer() {
        return questionanswer;
    }

    public void setQuestionanswer(String questionanswer) {
        this.questionanswer = questionanswer == null ? null : questionanswer.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getOptiontype() {
        return optiontype;
    }

    public void setOptiontype(Integer optiontype) {
        this.optiontype = optiontype;
    }

    public String getAnswera() {
        return answera;
    }

    public void setAnswera(String answera) {
        this.answera = answera == null ? null : answera.trim();
    }

    public String getAnswerb() {
        return answerb;
    }

    public void setAnswerb(String answerb) {
        this.answerb = answerb == null ? null : answerb.trim();
    }

    public String getAnswerc() {
        return answerc;
    }

    public void setAnswerc(String answerc) {
        this.answerc = answerc == null ? null : answerc.trim();
    }

    public String getAnswerd() {
        return answerd;
    }

    public void setAnswerd(String answerd) {
        this.answerd = answerd == null ? null : answerd.trim();
    }

    public String getAnswere() {
        return answere;
    }

    public void setAnswere(String answere) {
        this.answere = answere == null ? null : answere.trim();
    }

    public String getAnswerf() {
        return answerf;
    }

    public void setAnswerf(String answerf) {
        this.answerf = answerf == null ? null : answerf.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getQuestionanalysis() {
        return questionanalysis;
    }

    public void setQuestionanalysis(String questionanalysis) {
        this.questionanalysis = questionanalysis == null ? null : questionanalysis.trim();
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath == null ? null : imgpath.trim();
    }

    public String getLorepoint() {
        return lorepoint;
    }

    public void setLorepoint(String lorepoint) {
        this.lorepoint = lorepoint == null ? null : lorepoint.trim();
    }

    public String getLorepointt() {
        return lorepointt;
    }

    public void setLorepointt(String lorepointt) {
        this.lorepointt = lorepointt == null ? null : lorepointt.trim();
    }

    public String getLorepointth() {
        return lorepointth;
    }

    public void setLorepointth(String lorepointth) {
        this.lorepointth = lorepointth == null ? null : lorepointth.trim();
    }
}