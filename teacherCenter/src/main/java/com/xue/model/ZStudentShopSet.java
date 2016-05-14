/*
 * ZStudentShopSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

import java.util.Date;

/**
 * 学员已购买的商品标识
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class ZStudentShopSet {
    private Integer id;

    /**
    /*学员id
    */
    private String studentId;

    /**
    /*已购买的商品id
    */
    private String shopId;

    private String username1;

    private Date openTime;

    private Float openMoney;

    /**
    /*zonge
    */
    private Float spMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1 == null ? null : username1.trim();
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Float getOpenMoney() {
        return openMoney;
    }

    public void setOpenMoney(Float openMoney) {
        this.openMoney = openMoney;
    }

    public Float getSpMoney() {
        return spMoney;
    }

    public void setSpMoney(Float spMoney) {
        this.spMoney = spMoney;
    }
}