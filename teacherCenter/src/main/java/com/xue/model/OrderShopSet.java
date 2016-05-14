/*
 * Shop.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-10-20 Created
 */
package com.xue.model;

import java.util.Date;

/**
 * 订单商品关系表
 * 
 * @author Mr.Zhang
 * @version 1.0 2015-12-18
 */
public class OrderShopSet {
	
	private Integer id;
	
    /**
    /*商品id
    */
    private String shopId;

    /**
    /*试卷id
    */
    private Integer orderId;
    
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}