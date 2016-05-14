/*
 * CourseMajorSubject.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

import java.util.Date;

/**
 * 科目表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class CourseMajorSubject {
    /**
    /*科目id
    */
    private String subjectId;

    /**
    /*科目名称
    */
    private String subjectName;

    /**
    /*专业分类表id
    */
    private String majorId;

    /**
    /*操作人id
    */
    private String userId;

    /**
    /*学习顺序
    */
    private Integer studySort;

    /**
    /*是否删除（0：未删除 1：删除）
    */
    private String isDelete;

    /**
    /*是否标记实务课（0：不是   1：是）
    */
    private String isAironet;

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
    /*科目学习顺序（由低到高，越低优先级越高）
    */
    private Integer orderBy;

    /**
    /*科目类型（1.智能网课。2.直播网课）
    */
    private Integer subjectType;

    /**
    /*备注
    */
    private String remarks;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getStudySort() {
        return studySort;
    }

    public void setStudySort(Integer studySort) {
        this.studySort = studySort;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public String getIsAironet() {
        return isAironet;
    }

    public void setIsAironet(String isAironet) {
        this.isAironet = isAironet == null ? null : isAironet.trim();
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

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(Integer subjectType) {
        this.subjectType = subjectType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}