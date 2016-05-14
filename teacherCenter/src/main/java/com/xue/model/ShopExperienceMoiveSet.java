/*
 * ShopExperienceMoiveSet.java
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
public class ShopExperienceMoiveSet {
    private Integer id;

    /**
    /*商品id
    */
    private String shopId;

    /**
    /*试听视频id
    */
    private Integer experienceMoiveId;

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

    public Integer getExperienceMoiveId() {
        return experienceMoiveId;
    }

    public void setExperienceMoiveId(Integer experienceMoiveId) {
        this.experienceMoiveId = experienceMoiveId;
    }
}