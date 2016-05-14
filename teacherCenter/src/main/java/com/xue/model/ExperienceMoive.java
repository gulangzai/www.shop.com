/*
 * ExperienceMoive.java
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
public class ExperienceMoive {
    /**
    /*试看视频id
    */
    private Integer experienceMoiveId;

    /**
    /*试看视频名称
    */
    private String experienceMoiveName;

    /**
    /*视频url地址
    */
    private String experienceUrl;

    /**
    /*视频顺序
    */
    private Integer experienceOrder;

    /**
    /*阶段（1-7）分组
    */
    private Integer experiencePhase;

    /**
    /*试听专业ID
    */
    private String experienceMajorId;

    public Integer getExperienceMoiveId() {
        return experienceMoiveId;
    }

    public void setExperienceMoiveId(Integer experienceMoiveId) {
        this.experienceMoiveId = experienceMoiveId;
    }

    public String getExperienceMoiveName() {
        return experienceMoiveName;
    }

    public void setExperienceMoiveName(String experienceMoiveName) {
        this.experienceMoiveName = experienceMoiveName == null ? null : experienceMoiveName.trim();
    }

    public String getExperienceUrl() {
        return experienceUrl;
    }

    public void setExperienceUrl(String experienceUrl) {
        this.experienceUrl = experienceUrl == null ? null : experienceUrl.trim();
    }

    public Integer getExperienceOrder() {
        return experienceOrder;
    }

    public void setExperienceOrder(Integer experienceOrder) {
        this.experienceOrder = experienceOrder;
    }

    public Integer getExperiencePhase() {
        return experiencePhase;
    }

    public void setExperiencePhase(Integer experiencePhase) {
        this.experiencePhase = experiencePhase;
    }

    public String getExperienceMajorId() {
        return experienceMajorId;
    }

    public void setExperienceMajorId(String experienceMajorId) {
        this.experienceMajorId = experienceMajorId == null ? null : experienceMajorId.trim();
    }
}