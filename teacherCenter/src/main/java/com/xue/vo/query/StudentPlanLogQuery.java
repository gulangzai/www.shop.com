/*
 * StudentPlanLog.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-09-23 Created
 */
package com.xue.vo.query;

import java.util.Date;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-09-23
 */
public class StudentPlanLogQuery {
    /**
    /*主键
    */
    private Long id;

    /**
    /*学员id
    */
    private String studentId;

    /**
    /*学员名称
    */
    private String studentName;

    /**
    /*操作动作
    */
    private String actionName;

    /**
    /*创建时间
    */
    private Date createTime;

    /**
    /*学员计划id
    */
    private Integer planId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName == null ? null : actionName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }
}