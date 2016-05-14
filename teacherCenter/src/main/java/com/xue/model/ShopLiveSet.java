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
 * 商品直播详情关系表
 * 
 * @author Mr.Zhang
 * @version 1.0 2015-12-22
 */
public class ShopLiveSet {
	
	private Integer id;
	
    /**
    /*商品id
    */
    private String shopId;

    /**
    /*直播详情id
    */
    private Integer shopLiveId;
    
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

	public Integer getShopLiveId() {
		return shopLiveId;
	}

	public void setShopLiveId(Integer shopLiveId) {
		this.shopLiveId = shopLiveId;
	}

}