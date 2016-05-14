/*
 * StudentPlanLearn.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 我的计划学习数据表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class StudentPlanLearn {
    /**
    /*我的计划学习id
    */
    private Integer planLearnId;

    /**
    /*完成进度数值
    */
    private Integer tempo;

    /**
    /*总学习时长
    */
    private Float totalLearnTime;

    /**
    /*一轮学习时长
    */
    private Float oneLearnTime;

    /**
    /*二轮学习时长
    */
    private Float twoLearnTime;

    /**
    /*三轮及以上学习时长
    */
    private Float threeLearnTime;

    /**
    /*复习率(12.59%)二轮学习的知识点数量
    */
    private Float reviewRate;

    /**
    /*总答题量
    */
    private Integer alwaysATopicQuantity;

    /**
    /*正确数量
    */
    private Float correct;

    /**
    /*总共计划知识点
    */
    private Float knowledgePointCoverage;

    /**
    /*二次做题的知识点数量
    */
    private Float twoPracticeRate;

    /**
    /*答对数量
    */
    private Float threePracticeRate;

    public Integer getPlanLearnId() {
        return planLearnId;
    }

    public void setPlanLearnId(Integer planLearnId) {
        this.planLearnId = planLearnId;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public Float getTotalLearnTime() {
        return totalLearnTime;
    }

    public void setTotalLearnTime(Float totalLearnTime) {
        this.totalLearnTime = totalLearnTime;
    }

    public Float getOneLearnTime() {
        return oneLearnTime;
    }

    public void setOneLearnTime(Float oneLearnTime) {
        this.oneLearnTime = oneLearnTime;
    }

    public Float getTwoLearnTime() {
        return twoLearnTime;
    }

    public void setTwoLearnTime(Float twoLearnTime) {
        this.twoLearnTime = twoLearnTime;
    }

    public Float getThreeLearnTime() {
        return threeLearnTime;
    }

    public void setThreeLearnTime(Float threeLearnTime) {
        this.threeLearnTime = threeLearnTime;
    }

    public Float getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(Float reviewRate) {
        this.reviewRate = reviewRate;
    }

    public Integer getAlwaysATopicQuantity() {
        return alwaysATopicQuantity;
    }

    public void setAlwaysATopicQuantity(Integer alwaysATopicQuantity) {
        this.alwaysATopicQuantity = alwaysATopicQuantity;
    }

    public Float getCorrect() {
        return correct;
    }

    public void setCorrect(Float correct) {
        this.correct = correct;
    }

    public Float getKnowledgePointCoverage() {
        return knowledgePointCoverage;
    }

    public void setKnowledgePointCoverage(Float knowledgePointCoverage) {
        this.knowledgePointCoverage = knowledgePointCoverage;
    }

    public Float getTwoPracticeRate() {
        return twoPracticeRate;
    }

    public void setTwoPracticeRate(Float twoPracticeRate) {
        this.twoPracticeRate = twoPracticeRate;
    }

    public Float getThreePracticeRate() {
        return threePracticeRate;
    }

    public void setThreePracticeRate(Float threePracticeRate) {
        this.threePracticeRate = threePracticeRate;
    }
 
	
	public float coverage;
	
	public float pie;

	public float getCoverage() {
		return coverage;
	}

	public void setCoverage(float coverage) {
		this.coverage = coverage;
	}

	public float getPie() {
		return pie;
	}

	public void setPie(float pie) {
		this.pie = pie;
	}
	
	
}