/*
 * StudentDetailPoint.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 学员每天知识点表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class StudentDetailPoint {
    /**
    /*每天计划详细知识点id
    */
    private Integer detailPointId;

    /**
    /*学员每天知识点解析
    */
    private String detailPointContent;

    /**
    /*学员每天知识点解析
    */
    private String detailPointExplian;

    /**
    /*视频链接
    */
    private String liveLink;

    /**
    /*学习状态（0.未学习.1已学习.2学习中）
    */
    private Integer stuta;

    /**
    /*课件id
    */
    private String courseWareid;

    /**
    /*知识点id
    */
    private String pointId;

    /**
    /*章id
    */
    private String chapterId;

    /**
    /*节id
    */
    private String sectionId;

    /**
    /*学习顺序
    */
    private Integer orderby;

    /**
    /*知识点是否更新（0：未更新，1：已更新）
    */
    private Integer isUpdate;

    /**
    /*做题数量
    */
    private Integer doSubjectNumber;

    /**
    /*做题错误次数
    */
    private Integer doSubjectErrorNumber;

    /**
    /*错误率
    */
    private Integer errorRate;

    /**
    /*笔记A内容
    */
    private String notsa;

    /**
    /*笔记A记录时间
    */
    private String notsaTime;

    /**
    /*笔记B内容
    */
    private String notsb;

    /**
    /*笔记B记录时间
    */
    private String notsbTime;

    /**
    /*笔记C内容
    */
    private String notsc;

    /**
    /*笔记C记录时间
    */
    private String notscTime;

    /**
    /*笔记记录次数(只可记录3次，超过3次则覆盖其中一个)
    */
    private Integer notsNumber;

    public Integer getDetailPointId() {
        return detailPointId;
    }

    public void setDetailPointId(Integer detailPointId) {
        this.detailPointId = detailPointId;
    }

    public String getDetailPointContent() {
        return detailPointContent;
    }

    public void setDetailPointContent(String detailPointContent) {
        this.detailPointContent = detailPointContent == null ? null : detailPointContent.trim();
    }

    public String getDetailPointExplian() {
        return detailPointExplian;
    }

    public void setDetailPointExplian(String detailPointExplian) {
        this.detailPointExplian = detailPointExplian == null ? null : detailPointExplian.trim();
    }

    public String getLiveLink() {
        return liveLink;
    }

    public void setLiveLink(String liveLink) {
        this.liveLink = liveLink == null ? null : liveLink.trim();
    }

    public Integer getStuta() {
        return stuta;
    }

    public void setStuta(Integer stuta) {
        this.stuta = stuta;
    }

    public String getCourseWareid() {
        return courseWareid;
    }

    public void setCourseWareid(String courseWareid) {
        this.courseWareid = courseWareid == null ? null : courseWareid.trim();
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId == null ? null : pointId.trim();
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

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Integer getDoSubjectNumber() {
        return doSubjectNumber;
    }

    public void setDoSubjectNumber(Integer doSubjectNumber) {
        this.doSubjectNumber = doSubjectNumber;
    }

    public Integer getDoSubjectErrorNumber() {
        return doSubjectErrorNumber;
    }

    public void setDoSubjectErrorNumber(Integer doSubjectErrorNumber) {
        this.doSubjectErrorNumber = doSubjectErrorNumber;
    }

    public Integer getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(Integer errorRate) {
        this.errorRate = errorRate;
    }

    public String getNotsa() {
        return notsa;
    }

    public void setNotsa(String notsa) {
        this.notsa = notsa == null ? null : notsa.trim();
    }

    public String getNotsaTime() {
        return notsaTime;
    }

    public void setNotsaTime(String notsaTime) {
        this.notsaTime = notsaTime == null ? null : notsaTime.trim();
    }

    public String getNotsb() {
        return notsb;
    }

    public void setNotsb(String notsb) {
        this.notsb = notsb == null ? null : notsb.trim();
    }

    public String getNotsbTime() {
        return notsbTime;
    }

    public void setNotsbTime(String notsbTime) {
        this.notsbTime = notsbTime == null ? null : notsbTime.trim();
    }

    public String getNotsc() {
        return notsc;
    }

    public void setNotsc(String notsc) {
        this.notsc = notsc == null ? null : notsc.trim();
    }

    public String getNotscTime() {
        return notscTime;
    }

    public void setNotscTime(String notscTime) {
        this.notscTime = notscTime == null ? null : notscTime.trim();
    }

    public Integer getNotsNumber() {
        return notsNumber;
    }

    public void setNotsNumber(Integer notsNumber) {
        this.notsNumber = notsNumber;
    }
}