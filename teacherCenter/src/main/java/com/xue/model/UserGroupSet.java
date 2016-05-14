/*
 * UserGroupSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 用户所属角色定义
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class UserGroupSet {
    /**
    /*关联id
    */
    private String id;

    /**
    /*用户id
    */
    private String userId;

    /**
    /*角色id
    */
    private String roleId;

    /**
    /*是否删除（0:未删除 1：删除）
    */
    private String isDel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }
}