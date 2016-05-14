/*
 * BsUserNoticSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2016-01-17 Created
 */
package com.xue.model;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2016-01-17
 */
public class BsUserNoticSet extends BsUserNoticSetKey {
    /**
    /*公告状态（0.未读。1已读。）
    */
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}