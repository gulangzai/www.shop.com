/*
 * StudentShopDetailSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 购买商品班级阶段表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class StudentShopDetailSet {
    /**
    /*学员购买班级阶段学习id
    */
    private Integer id;

    /**
    /*学员id
    */
    private String studentId;

    /**
    /*商品班级阶段id
    */
    private Integer shopDetailId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Integer getShopDetailId() {
        return shopDetailId;
    }

    public void setShopDetailId(Integer shopDetailId) {
        this.shopDetailId = shopDetailId;
    }
}