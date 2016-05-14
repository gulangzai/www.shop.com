/*
 * Entitypage.java
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
public class Entitypage {
    private Integer processbid;

    /**
    /*错误题数
    */
    private Integer questnum;

    /**
    /*正确题数
    */
    private Integer rightnum;

    /**
    /*试卷得分
    */
    private Float score;

    /**
    /*总分
    */
    private Integer allscore;

    /**
    /*操作人id
    */
    private String userid;

    /**
    /*名次
    */
    private Integer ranking;

    /**
    /*击败了多少人
    */
    private Integer beat;

    private Integer status;

    private String enddate;

    private Integer pagetype;

    public Integer getProcessbid() {
        return processbid;
    }

    public void setProcessbid(Integer processbid) {
        this.processbid = processbid;
    }

    public Integer getQuestnum() {
        return questnum;
    }

    public void setQuestnum(Integer questnum) {
        this.questnum = questnum;
    }

    public Integer getRightnum() {
        return rightnum;
    }

    public void setRightnum(Integer rightnum) {
        this.rightnum = rightnum;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getAllscore() {
        return allscore;
    }

    public void setAllscore(Integer allscore) {
        this.allscore = allscore;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
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

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate == null ? null : enddate.trim();
    }

    public Integer getPagetype() {
        return pagetype;
    }

    public void setPagetype(Integer pagetype) {
        this.pagetype = pagetype;
    }
}