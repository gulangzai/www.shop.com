/*
 * Order.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

import java.util.Date;

/**
 * 订单信息数据
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class Order {
    /**
    /*订单id
    */
    private Integer orderId;

    /**
    /*折扣
    */
    private Float disCount;

    /**
    /*快递单位
    */
    private String expressCommPany;

    /**
    /*快递单号
    */
    private String expressNo;

    /**
    /*订单生成日期
    */
    private Date orderCreateDate;

    /**
    /*订单描述
    */
    private String orderDesc;

    /**
    /*订单号
    */
    private String orderNo;

    /**
    /*订单数量
    */
    private Integer orderNum;

    /**
    /*订单价格
    */
    private Float orderPrice;

    /**
    /*订单状态（0:未付款  1:已付款  2:已过期  3:完成  5：支付中 9:购物车数据）
    */
    private String orderStatus;

    /**
    /*订单类型((0:电子书 1：实体书 2：课件 3：直播;4：在线模考数据))
    */
    private String orderType;

    /**
    /*订单用户id
    */
    private String orderUserId;

    /**
    /*是否是管理员（后台开课）0：不是（就是学员订单）1：是
    */
    private String isUser;

    /**
    /*有效期
    */
    private Date validity;

    /**
    /*手机号
    */
    private String mobile;

    /**
    /*支付类型（001：支付宝；002：快钱；003：易宝；004：后台）
    */
    private String payType;

    /**
    /*收货地址id
    */
    private String addressId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Float getDisCount() {
        return disCount;
    }

    public void setDisCount(Float disCount) {
        this.disCount = disCount;
    }

    public String getExpressCommPany() {
        return expressCommPany;
    }

    public void setExpressCommPany(String expressCommPany) {
        this.expressCommPany = expressCommPany == null ? null : expressCommPany.trim();
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public Date getOrderCreateDate() {
        return orderCreateDate;
    }

    public void setOrderCreateDate(Date orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc == null ? null : orderDesc.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(String orderUserId) {
        this.orderUserId = orderUserId == null ? null : orderUserId.trim();
    }

    public String getIsUser() {
        return isUser;
    }

    public void setIsUser(String isUser) {
        this.isUser = isUser == null ? null : isUser.trim();
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId == null ? null : addressId.trim();
    }
}