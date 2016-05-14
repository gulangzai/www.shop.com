/*
 * BsRoleFunctionSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 角色功能（权限）表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class BsRoleFunctionSet {
    /**
    /*id
    */
    private Integer id;

    /**
    /*角色id
    */
    private Integer roleId;

    /**
    /*功能（菜单）id
    */
    private Integer functionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }
}