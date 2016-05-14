/*
 * SysCollect.java
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
public class SysCollect {
    /**
    /*系统收藏夹id
    */
    private Integer sysCollectId;

    /**
    /*系统收藏夹名称
    */
    private String sysCollectName;

    /**
    /*包id
    */
    private Integer sysPackectId;

    /**
    /*包名称
    */
    private String sysPackectName;

    /**
    /*所属知识点id
    */
    private Integer pointId;

    /**
    /*是否删除
    */
    private Integer isDel;

    /**
    /*删除操作人id
    */
    private String delUser;

    /**
    /*删除时间
    */
    private Date delTime;

    public Integer getSysCollectId() {
        return sysCollectId;
    }

    public void setSysCollectId(Integer sysCollectId) {
        this.sysCollectId = sysCollectId;
    }

    public String getSysCollectName() {
        return sysCollectName;
    }

    public void setSysCollectName(String sysCollectName) {
        this.sysCollectName = sysCollectName == null ? null : sysCollectName.trim();
    }

    public Integer getSysPackectId() {
        return sysPackectId;
    }

    public void setSysPackectId(Integer sysPackectId) {
        this.sysPackectId = sysPackectId;
    }

    public String getSysPackectName() {
        return sysPackectName;
    }

    public void setSysPackectName(String sysPackectName) {
        this.sysPackectName = sysPackectName == null ? null : sysPackectName.trim();
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getDelUser() {
        return delUser;
    }

    public void setDelUser(String delUser) {
        this.delUser = delUser == null ? null : delUser.trim();
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }
}