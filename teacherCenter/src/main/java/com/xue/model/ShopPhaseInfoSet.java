/*
 * ShopPhaseInfoSet.java
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
public class ShopPhaseInfoSet {
    private Integer id;

    /**
    /*科目id
    */
    private String shopId;

    /**
    /*阶段id
    */
    private Integer phaseInfoId;

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

    public Integer getPhaseInfoId() {
        return phaseInfoId;
    }

    public void setPhaseInfoId(Integer phaseInfoId) {
        this.phaseInfoId = phaseInfoId;
    }
}