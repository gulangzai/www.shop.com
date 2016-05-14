/*
 * PicHomepage.java
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
public class PicHomepage {
    private Integer id;

    /**
    /*顺序
    */
    private Integer sequence;

    /**
    /*图片名称
    */
    private String picName;

    /**
    /*图片链接
    */
    private String picLink;

    /**
    /*图片真实名称
    */
    private String picRename;

    /**
    /*图片URL
    */
    private String picUrl;

    /**
    /*是否展示（0：是，1：否）
    */
    private Integer isShow;

    /**
    /*添加时间
    */
    private Date addTime;

    /**
    /*添加人ID
    */
    private String addUser;

    /**
    /*结束时间
    */
    private Date endTime;

    /**
    /*开始时间
    */
    private Date startTime;

    private String picPos;

    private String by1;

    private String by2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName == null ? null : picName.trim();
    }

    public String getPicLink() {
        return picLink;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink == null ? null : picLink.trim();
    }

    public String getPicRename() {
        return picRename;
    }

    public void setPicRename(String picRename) {
        this.picRename = picRename == null ? null : picRename.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser == null ? null : addUser.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getPicPos() {
        return picPos;
    }

    public void setPicPos(String picPos) {
        this.picPos = picPos == null ? null : picPos.trim();
    }

    public String getBy1() {
        return by1;
    }

    public void setBy1(String by1) {
        this.by1 = by1 == null ? null : by1.trim();
    }

    public String getBy2() {
        return by2;
    }

    public void setBy2(String by2) {
        this.by2 = by2 == null ? null : by2.trim();
    }
}