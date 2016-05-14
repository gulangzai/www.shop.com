/*
 * CollectPhone.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 手机采集
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class CollectPhone {
    private Integer id;

    private String phone;

    private String username;

    private String feedbacktext;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getFeedbacktext() {
        return feedbacktext;
    }

    public void setFeedbacktext(String feedbacktext) {
        this.feedbacktext = feedbacktext == null ? null : feedbacktext.trim();
    }
}