/*
 * BsuserOpinionSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2016-01-27 Created
 */
package com.xue.model;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2016-01-27
 */
public class BsuserOpinionSet {
    /**
    /*用户角色问题关联表id
    */
    private Integer id;

    /**
    /*用户id
    */
    private String userId;

    /**
    /*角色id
    */
    private Integer roleId;

    /**
    /*问题id
    */
    private Integer opinionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(Integer opinionId) {
        this.opinionId = opinionId;
    }
}