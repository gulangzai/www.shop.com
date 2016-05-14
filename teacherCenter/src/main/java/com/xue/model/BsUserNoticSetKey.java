/*
 * BsUserNoticSetKey.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2016-01-17 Created
 */
package com.xue.model;

public class BsUserNoticSetKey {
    private String userId;

    /**
    /*公告id
    */
    private Integer noticId;
    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getNoticId() {
        return noticId;
    }

    public void setNoticId(Integer noticId) {
        this.noticId = noticId;
    }
}