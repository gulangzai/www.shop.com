/*
 * CourseWare.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

import java.util.Date;

/**
 * 存储课件数据
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class CourseWare {
    /**
    /*课件id
    */
    private String coursewareId;

    /**
    /*章节描述
    */
    private String chilDescribe;

    /**
    /*课件集id
    */
    private String courseCollectId;

    /**
    /*课件标题
    */
    private String title;

    /**
    /*知识点描述
    */
    private String knowledgeDesc;

    /**
    /*课件时间长度
    */
    private Float knowledgeTime;

    /**
    /*制作人
    */
    private String makePeople;

    /**
    /*收费类型（0，免费。1收费）
    */
    private Integer payType;

    /**
    /*绑定播放地址
    */
    private String playUrl;

    /**
    /*章节描述
    */
    private String setopm;

    /**
    /*排序日期（时间戳）
    */
    private Date sortDate;

    /**
    /*排序号
    */
    private Integer sortNumber;

    /**
    /*是否删除（0，不删除。1，删除）
    */
    private Integer isDel;

    public String getCoursewareId() {
        return coursewareId;
    }

    public void setCoursewareId(String coursewareId) {
        this.coursewareId = coursewareId == null ? null : coursewareId.trim();
    }

    public String getChilDescribe() {
        return chilDescribe;
    }

    public void setChilDescribe(String chilDescribe) {
        this.chilDescribe = chilDescribe == null ? null : chilDescribe.trim();
    }

    public String getCourseCollectId() {
        return courseCollectId;
    }

    public void setCourseCollectId(String courseCollectId) {
        this.courseCollectId = courseCollectId == null ? null : courseCollectId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getKnowledgeDesc() {
        return knowledgeDesc;
    }

    public void setKnowledgeDesc(String knowledgeDesc) {
        this.knowledgeDesc = knowledgeDesc == null ? null : knowledgeDesc.trim();
    }

    public Float getKnowledgeTime() {
        return knowledgeTime;
    }

    public void setKnowledgeTime(Float knowledgeTime) {
        this.knowledgeTime = knowledgeTime;
    }

    public String getMakePeople() {
        return makePeople;
    }

    public void setMakePeople(String makePeople) {
        this.makePeople = makePeople == null ? null : makePeople.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl == null ? null : playUrl.trim();
    }

    public String getSetopm() {
        return setopm;
    }

    public void setSetopm(String setopm) {
        this.setopm = setopm == null ? null : setopm.trim();
    }

    public Date getSortDate() {
        return sortDate;
    }

    public void setSortDate(Date sortDate) {
        this.sortDate = sortDate;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}