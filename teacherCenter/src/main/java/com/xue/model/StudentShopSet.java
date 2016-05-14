/*
 * StudentShopSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-09-10 Created
 */
package com.xue.model;

/**
 * 学员已购买的商品标识
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-09-10
 */
public class StudentShopSet {
    private Integer id;

    /**
    /*学员id
    */
    private String studentId;

    /**
    /*已购买的商品id
    */
    private String shopId;
    
    public StudentShopSet(){}

    public StudentShopSet(String studentId, String shopId) {
		// TODO Auto-generated constructor stub
    	this.studentId = studentId;
    	this.shopId = shopId;
	}
    
    public StudentShopSet(Integer id,String studentId, String shopId) {
		// TODO Auto-generated constructor stub
    	this(studentId,shopId);
    	this.id = id;
	}

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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }
}