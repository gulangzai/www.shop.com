/*
 * FlyInfo.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 视频信息表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class FlyInfo {
    /**
    /*视频id
    */
    private Integer flyId;

    /**
    /*视频名称
    */
    private String flyName;

    /**
    /*视频来源
    */
    private String flySource;

    /**
    /*视频路径
    */
    private String flyUrl;

    /**
    /*创建时间
    */
    private String createTime;

    /**
    /*创建人id（所属user表）
    */
    private String createUserId;

    /**
    /*修改时间
    */
    private String updateTime;

    /**
    /*修改人id
    */
    private String updateUserId;

    /**
    /*是否删除（0未删除默认，1已删除）
    */
    private Integer isDel;

    /**
    /*删除操作人id
    */
    private String delUserId;

    /**
    /*标签1
    */
    private String labelA;

    /**
    /*标签2
    */
    private String labelB;

    private String labelC;

    private String labelD;

    private String labelE;

    private String labelF;

    private String labelG;

    private String labelH;

    public Integer getFlyId() {
        return flyId;
    }

    public void setFlyId(Integer flyId) {
        this.flyId = flyId;
    }

    public String getFlyName() {
        return flyName;
    }

    public void setFlyName(String flyName) {
        this.flyName = flyName == null ? null : flyName.trim();
    }

    public String getFlySource() {
        return flySource;
    }

    public void setFlySource(String flySource) {
        this.flySource = flySource == null ? null : flySource.trim();
    }

    public String getFlyUrl() {
        return flyUrl;
    }

    public void setFlyUrl(String flyUrl) {
        this.flyUrl = flyUrl == null ? null : flyUrl.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getDelUserId() {
        return delUserId;
    }

    public void setDelUserId(String delUserId) {
        this.delUserId = delUserId == null ? null : delUserId.trim();
    }

    public String getLabelA() {
        return labelA;
    }

    public void setLabelA(String labelA) {
        this.labelA = labelA == null ? null : labelA.trim();
    }

    public String getLabelB() {
        return labelB;
    }

    public void setLabelB(String labelB) {
        this.labelB = labelB == null ? null : labelB.trim();
    }

    public String getLabelC() {
        return labelC;
    }

    public void setLabelC(String labelC) {
        this.labelC = labelC == null ? null : labelC.trim();
    }

    public String getLabelD() {
        return labelD;
    }

    public void setLabelD(String labelD) {
        this.labelD = labelD == null ? null : labelD.trim();
    }

    public String getLabelE() {
        return labelE;
    }

    public void setLabelE(String labelE) {
        this.labelE = labelE == null ? null : labelE.trim();
    }

    public String getLabelF() {
        return labelF;
    }

    public void setLabelF(String labelF) {
        this.labelF = labelF == null ? null : labelF.trim();
    }

    public String getLabelG() {
        return labelG;
    }

    public void setLabelG(String labelG) {
        this.labelG = labelG == null ? null : labelG.trim();
    }

    public String getLabelH() {
        return labelH;
    }

    public void setLabelH(String labelH) {
        this.labelH = labelH == null ? null : labelH.trim();
    }
}