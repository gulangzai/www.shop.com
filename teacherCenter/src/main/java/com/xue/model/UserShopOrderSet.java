/*
 * UserShopOrderSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 用户商品订单表（订单支付后，要更新该表）
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class UserShopOrderSet {
    /**
    /*用户id
    */
    private String userId;

    /**
    /*商品id
    */
    private String shopId;

    /**
    /*订单id
    */
    private String orderId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }
}