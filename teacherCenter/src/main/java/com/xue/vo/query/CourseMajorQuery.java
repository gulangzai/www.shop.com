/*
 * CourseMajor.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-10-10 Created
 */
package com.xue.vo.query;

import java.util.Date;

/**
 *  专业课程分类表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-10-10
 */
public class CourseMajorQuery {
    /**
    /*专业id
    */
    private String majorId;

    /**
    /*专业分类课程名称
    */
    private String majorName;

    /**
    /*是否删除（0：未删除 1：删除）
    */
    private String isDelete;

    private String courseId;

    /**
    /*操作人id
    */
    private String userId;

    /**
    /*创建时间
    */
    private Date createTime;

    /**
    /*修改时间
    */
    private Date updateTime;

    /**
    /*删除时间
    */
    private Date deleteTime;

    /**
    /*实务统称(待定)
    */
    private String shiwutongcheng;

    private String picUrl;

    private Integer sequence;

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getShiwutongcheng() {
        return shiwutongcheng;
    }

    public void setShiwutongcheng(String shiwutongcheng) {
        this.shiwutongcheng = shiwutongcheng == null ? null : shiwutongcheng.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}