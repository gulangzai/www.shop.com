/*
 * Live.java
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
public class Live {
    /**
    /*直播id
    */
    private String liveId;

    /**
    /*直播内容
    */
    private String liveContent;

    /**
    /*直播日期
    */
    private Date liveDate;

    /**
    /*直播链接
    */
    private String liveLink;

    /**
    /*直播密码
    */
    private String livePwd;

    /**
    /*直播集id
    */
    private String liveSetId;

    /**
    /*直播标题
    */
    private String liveTitle;

    /**
    /*直播顺序
    */
    private Integer liveSort;

    /**
    /*主讲老师
    */
    private String speaker;

    /**
    /*直播封面图片
    */
    private String liveCoverPic;

    /**
    /*收费方式（0:免费    1：收费）
    */
    private String payType;

    /**
    /*数据状态（0:未删除    1：删除）
    */
    private Integer status;

    /**
    /*科目id
    */
    private String subjectId;

    /**
    /*班级id
    */
    private String classesId;

    /**
    /*课程id
    */
    private String courseId;

    /**
    /*专业id
    */
    private String majorId;

    /**
    /*直播时间长度
    */
    private Float duration;

    /**
    /*是否设置首页（0，否。1，是）
    */
    private Integer isHomePage;

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId == null ? null : liveId.trim();
    }

    public String getLiveContent() {
        return liveContent;
    }

    public void setLiveContent(String liveContent) {
        this.liveContent = liveContent == null ? null : liveContent.trim();
    }

    public Date getLiveDate() {
        return liveDate;
    }

    public void setLiveDate(Date liveDate) {
        this.liveDate = liveDate;
    }

    public String getLiveLink() {
        return liveLink;
    }

    public void setLiveLink(String liveLink) {
        this.liveLink = liveLink == null ? null : liveLink.trim();
    }

    public String getLivePwd() {
        return livePwd;
    }

    public void setLivePwd(String livePwd) {
        this.livePwd = livePwd == null ? null : livePwd.trim();
    }

    public String getLiveSetId() {
        return liveSetId;
    }

    public void setLiveSetId(String liveSetId) {
        this.liveSetId = liveSetId == null ? null : liveSetId.trim();
    }

    public String getLiveTitle() {
        return liveTitle;
    }

    public void setLiveTitle(String liveTitle) {
        this.liveTitle = liveTitle == null ? null : liveTitle.trim();
    }

    public Integer getLiveSort() {
        return liveSort;
    }

    public void setLiveSort(Integer liveSort) {
        this.liveSort = liveSort;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker == null ? null : speaker.trim();
    }

    public String getLiveCoverPic() {
        return liveCoverPic;
    }

    public void setLiveCoverPic(String liveCoverPic) {
        this.liveCoverPic = liveCoverPic == null ? null : liveCoverPic.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId == null ? null : classesId.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Integer getIsHomePage() {
        return isHomePage;
    }

    public void setIsHomePage(Integer isHomePage) {
        this.isHomePage = isHomePage;
    }
}