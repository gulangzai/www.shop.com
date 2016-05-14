/*
 * KnowledgePoint.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-09-10 Created
 */
package com.xue.vo.query;

/**
 * 知识点表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-09-10
 */
public class KnowledgePointQuery {
    /**
    /*知识点id
    */
    private Integer pointId;

    private String chapterId;

    private String sectionId;

    /**
    /*知识点内容
    */
    private String pointContent;

    /**
    /*解析
    */
    private String explain;

    /**
    /*课件id
    */
    private String courseWareid;

    /**
    /*视频链接地址
    */
    private String liveLink;

    /**
    /*是否删除（0:未删除 1：删除）
    */
    private Integer isDel;

    
    /**
    /*CC视频id
    */
    private String liveCcId;
    
    
    public String getLiveCcId() {
		return liveCcId;
	}

	public void setLiveCcId(String liveCcId) {
		this.liveCcId = liveCcId;
	}

	public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
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

    public String getPointContent() {
        return pointContent;
    }

    public void setPointContent(String pointContent) {
        this.pointContent = pointContent == null ? null : pointContent.trim();
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }

    public String getCourseWareid() {
        return courseWareid;
    }

    public void setCourseWareid(String courseWareid) {
        this.courseWareid = courseWareid == null ? null : courseWareid.trim();
    }

    public String getLiveLink() {
        return liveLink;
    }

    public void setLiveLink(String liveLink) {
        this.liveLink = liveLink == null ? null : liveLink.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}