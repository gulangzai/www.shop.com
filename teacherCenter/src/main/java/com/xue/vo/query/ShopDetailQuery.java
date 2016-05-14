/*
 * ShopDetail.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-09-10 Created
 */
package com.xue.vo.query;

/**
 * 商品专业班型科目关联表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-09-10
 */
public class ShopDetailQuery {
    /**
    /*所属商品id
    */
    private Integer detailId;

    private String majorId;

    private String classesId;

    private String subjectId;

    private String majorName;

    private String claessName;

    private String subjectName;

    /**
    /*代码状态判断需要
    */
    private String statuJava;

    /**
    /*文档下载地址
    */
    private String uploadUrl;

    /**
    /*直播id
    */
    private String liveId;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId == null ? null : classesId.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    public String getClaessName() {
        return claessName;
    }

    public void setClaessName(String claessName) {
        this.claessName = claessName == null ? null : claessName.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public String getStatuJava() {
        return statuJava;
    }

    public void setStatuJava(String statuJava) {
        this.statuJava = statuJava == null ? null : statuJava.trim();
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl == null ? null : uploadUrl.trim();
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId == null ? null : liveId.trim();
    }
}