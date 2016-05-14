/*
 * Footprint.java
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
public class Footprint {
    /**
    /*足迹id
    */
    private Integer footprint;

    /**
    /*足迹名称
    */
    private String eventName;

    /**
    /*所属商品详细id
    */
    private Integer shopDetailId;

    /**
    /*发生时间
    */
    private Date occurrenceTime;

    public Integer getFootprint() {
        return footprint;
    }

    public void setFootprint(Integer footprint) {
        this.footprint = footprint;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

    public Integer getShopDetailId() {
        return shopDetailId;
    }

    public void setShopDetailId(Integer shopDetailId) {
        this.shopDetailId = shopDetailId;
    }

    public Date getOccurrenceTime() {
        return occurrenceTime;
    }

    public void setOccurrenceTime(Date occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }
}