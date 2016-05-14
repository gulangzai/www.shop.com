/*
 * BsSubmenu.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class BsSubmenu {
    private Integer id;

    /**
    /*子菜单名称
    */
    private String submenuName;

    /**
    /*子菜单方法
    */
    private String submenuMethod;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubmenuName() {
        return submenuName;
    }

    public void setSubmenuName(String submenuName) {
        this.submenuName = submenuName == null ? null : submenuName.trim();
    }

    public String getSubmenuMethod() {
        return submenuMethod;
    }

    public void setSubmenuMethod(String submenuMethod) {
        this.submenuMethod = submenuMethod == null ? null : submenuMethod.trim();
    }
}