/*
 * OrderDetail.java
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
public class OrderDetail {
    private Integer id;

    /**
    /*商品id
    */
    private String shopId;

    /**
    /*商品详情页ID
    */
    private Integer shopDetailId;

    /**
    /*班级id
    */
    private String majorId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public Integer getShopDetailId() {
        return shopDetailId;
    }

    public void setShopDetailId(Integer shopDetailId) {
        this.shopDetailId = shopDetailId;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }
}