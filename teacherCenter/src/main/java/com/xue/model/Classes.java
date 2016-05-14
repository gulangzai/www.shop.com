/*
 * Classes.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-09-10 Created
 */
package com.xue.model;

import java.io.Serializable;

/**
 * 存储班级信息数据
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-09-10
 */
public class Classes implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 652200386867234321L;

	private String classesId;

    /**
    /*班级名称
    */
    private String classesName;

    /**
    /*班级描述
    */
    private String pemark;

    /**
    /*上级班级父id
    */
    private String parentId;

    /**
    /*是否显示在快速报名(0：否 1：是）
    */
    private String isQuickApply;

    /**
    /*是否删除（0：未删除 1：删除）
    */
    private String isDel;

    /**
    /*班级优先学习顺序（由低到高，越低优先级越高）
    */
    private String orderBy;
    
    /**
    /*班级类型（1.智能网课。2.直播网课）
    */
    private Integer classesType;

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId == null ? null : classesId.trim();
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName == null ? null : classesName.trim();
    }

    public String getPemark() {
        return pemark;
    }

    public void setPemark(String pemark) {
        this.pemark = pemark == null ? null : pemark.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getIsQuickApply() {
        return isQuickApply;
    }

    public void setIsQuickApply(String isQuickApply) {
        this.isQuickApply = isQuickApply == null ? null : isQuickApply.trim();
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy == null ? null : orderBy.trim();
    }

	public Integer getClassesType() {
		return classesType;
	}

	public void setClassesType(Integer classesType) {
		this.classesType = classesType;
	}
    
}