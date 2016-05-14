/*
 * MyCollect.java
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
public class MyCollect {
    /**
    /*我的收藏id
    */
    private Integer collectId;

    /**
    /*我的收藏夹名称
    */
    private String collectName;

    /**
    /*包id
    */
    private Integer packetId;

    /**
    /*包名
    */
    private String packetName;

    /**
    /*所属知识点id()
    */
    private Integer pointId;

    /**
    /*是否删除(0。未删除。1.已删除)
    */
    private Integer isDel;

    /**
    /*属于视频还是习题（0：视频 1：习题 9：系统默认）
    */
    private Integer status;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName == null ? null : collectName.trim();
    }

    public Integer getPacketId() {
        return packetId;
    }

    public void setPacketId(Integer packetId) {
        this.packetId = packetId;
    }

    public String getPacketName() {
        return packetName;
    }

    public void setPacketName(String packetName) {
        this.packetName = packetName == null ? null : packetName.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}